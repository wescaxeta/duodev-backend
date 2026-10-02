package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.enums.Status;
import com.duodev.duodevbackend.exceptions.ResourceNotFoundException;
import com.duodev.duodevbackend.model.Email;
import com.duodev.duodevbackend.model.Mentoria;
import com.duodev.duodevbackend.model.Sessao;
import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.repository.SessaoRepository;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Value;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
public class SessaoService {

    @Value("${application.url}")
    private String APPLICATION_URL;

    @Autowired
    private SessaoRepository sessaoRepository;

    @org.springframework.beans.factory.annotation.Value("${duodev.mail.username}")
    private String mailUsername;

    @Autowired
    private MentoriaService mentoriaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "DuoDev";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    @Autowired
    private ServletWebServerFactoryAutoConfiguration servletWebServerFactoryAutoConfiguration;

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = SessaoService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return credential;
    }

    public static Calendar getCalendarService() throws IOException {
        final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        Credential credential = getCredentials(HTTP_TRANSPORT);

        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

    }

    public Sessao createSessao(Sessao novaSessao, String emailMentor, String emailMentorado) {
        if (novaSessao.getDataHoraInicial().isAfter(novaSessao.getDataHoraFinal())) {
            throw new RuntimeException("A data de inicío não pode ser posterior que a data final.");
        } else if (novaSessao.getDataHoraInicial().isBefore(java.time.LocalDateTime.now())) {
            throw new RuntimeException("A data de inicío não pode ser anterior que a data atual.");
        } else {
            novaSessao.setStatus(Status.PENDENTE);
            novaSessao.setLinkMeet("pendente");
            novaSessao.setEventGoogleCalendarId("pendente");
            novaSessao.setInvite(createInviteId());
            novaSessao.setMentoria(null);
            sessaoRepository.save(novaSessao);

            sendInviteInEmail(emailMentor, novaSessao, emailMentorado);
            return novaSessao;
        }
    }


    public String sendInviteInEmail(String emailMentor, Sessao sessao, String emailMentorado) {
        Email email = new Email();
        email.setSendDate(LocalDateTime.now());
        String appUrl= "http://localhost";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String inicioSessaoFormatado = formatter.format(sessao.getDataHoraInicial());
        String fimSessaoFormatado    = formatter.format(sessao.getDataHoraFinal());
        String mensagem = "Olá, você foi convidado para uma mentoria no DuoDev com " + emailMentorado +
                " na data: " + inicioSessaoFormatado + " até às: " + fimSessaoFormatado
                + " acesse o link para aceitar o convite: " + appUrl + ":8080/sessao/aceitar/"
                + sessao.getInvite();



        email.setBody(mensagem);
        email.setSendFrom(mailUsername);
        email.setSubject("Convite para mentoria DuoDev");
        email.setRequestedBy(emailMentorado);
        email.setSendTo(emailMentor);
        email.setInviteId(sessao.getInvite());

        return emailService.sendEmailInvite(email, sessao);
    }


    public String createMeetSession(Sessao novaSessao, String emailMentorado, String emailMentor,
                                    String inviteId) throws IOException {

        String retorno = "";
        Calendar service = getCalendarService();
        com.google.api.services.calendar.model.Event event = new com.google.api.services.calendar.model.Event()
                .setSummary("DuoDev Mentoria")
                .setLocation("Google Meet")
                .setDescription("Encontro criado por DuoDev para a mentoria");


        // formato da data 2024-06-03T09:00:00-03:00
        String dataHoraInicial = novaSessao.getDataHoraInicial().toString() + ":00-03:00";

        DateTime startDateTime = new DateTime(dataHoraInicial);
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Sao_Paulo");
        event.setStart(start);

        // formato da data 2024-06-03T09:00:00-03:00
        String dataHoraFinal = novaSessao.getDataHoraFinal().toString() + ":00-03:00";
        DateTime endDateTime = new DateTime(dataHoraFinal);
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Sao_Paulo");
        event.setEnd(end);

        Event.Creator creator = new Event.Creator();
        creator.setEmail(emailMentor);
        event.setCreator(creator);

        Event.Organizer organizer = new Event.Organizer();
        organizer.setEmail(emailMentor);
        event.setOrganizer(organizer);

        EventAttendee[] attendees = new EventAttendee[]{
                new EventAttendee().setEmail(emailMentor),
                new EventAttendee().setEmail(emailMentorado),
        };

        event.setAttendees(Arrays.asList(attendees));
        String calendarId = "primary";
        // cria a reunião no meet

        ConferenceSolutionKey conferenceSolutionKey = new ConferenceSolutionKey();
        conferenceSolutionKey.setType("hangoutsMeet");
        CreateConferenceRequest createConferenceRequest = new CreateConferenceRequest();
        createConferenceRequest.setRequestId("sample-request-id");

        ConferenceData conferenceData = new ConferenceData();
        conferenceData.setCreateRequest(createConferenceRequest);
        event.setConferenceData(conferenceData);

        Event createdEvent = service.events().insert(calendarId, event).setConferenceDataVersion(1).execute();

        novaSessao.setStatus(Status.AGENDADO);
        novaSessao.setLinkMeet(createdEvent.getHangoutLink());
        novaSessao.setEventGoogleCalendarId(createdEvent.getId());
        sessaoRepository.save(novaSessao);

        retorno =  createdEvent.getHangoutLink();
        return retorno;
    }

    public String createInviteId(){
        return UUID.randomUUID().toString();
    }

    public Boolean validateInviteAndCreateMentoria(String inviteId) throws IOException {
        Sessao sessao = sessaoRepository.findByInvite(inviteId);
        Email email = emailService.getEmailByInviteId(inviteId);
        Usuario mentor = usuarioService.getUsuarioByEmail(email.getSendTo());
        Usuario mentorado = usuarioService.getUsuarioByEmail(email.getRequestedBy());

        if (mentor == null || mentorado == null) {
            return false;
        }

        if (sessao == null || sessao.getStatus() != Status.PENDENTE) {
            return false;
        } else  {
            Mentoria mentoria = new Mentoria();
            mentoria.setUsuarioMentor(mentor);
            mentoria.setUsuarioMentorado(mentorado);
            mentoria.setDataInicial(sessao.getDataHoraInicial().toLocalDate());

            mentoriaService.createMentoria(mentoria);

            sessao.setMentoria(mentoria);

            sessaoRepository.save(sessao);

            createMeetSession(sessao, mentorado.getEmail(), mentor.getEmail(), inviteId);
            return true;
        }
    }

    // tem que ver se deve estourar uma exceção mesmo
    public Sessao getSessaoById(int id) {
        return sessaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sessão não encontrada"));
    }

    public void deleteSessao(Integer id) {
        Sessao sessao = getSessaoById(id);

        try {
            Calendar service = getCalendarService();
            service.events().delete("primary", sessao.getEventGoogleCalendarId()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sessao.setStatus(Status.CANCELADO);
        sessaoRepository.save(sessao);

    }

    public List<Sessao> findByDataHoraInicial(LocalDateTime dataHoraInicial) {
        return sessaoRepository.findByDataHoraInicial(dataHoraInicial);
    }

    public List<Sessao> findByDataHoraFinal(LocalDateTime dataHoraFinal) {
        return sessaoRepository.findByDataHoraFinal(dataHoraFinal);
    }

    public List<Sessao> findByDataHoraInicialBetween(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal) {
        return sessaoRepository.findByDataHoraInicialBetween(dataHoraInicial, dataHoraFinal);
    }

    public List<Sessao> getSessoesByMentoria(Mentoria mentoria) {
        return sessaoRepository.findByMentoria(mentoria);
    }

    public List<Sessao> getSessoesByStatus(Status status) {
        return sessaoRepository.findByStatus(status);
    }

    public String acceptSessao(String inviteId) throws IOException {

        boolean validInvite = validateInviteAndCreateMentoria(inviteId);

        if (validInvite){
            Sessao sessao = sessaoRepository.findByInvite(inviteId);
            sessao.setStatus(Status.AGENDADO);
            sessaoRepository.save(sessao);

            return "Sessão aceita com sucesso!";
        } else {
            return "Sessão não encontrada ou já foi aceita.";
        }

    }
}


