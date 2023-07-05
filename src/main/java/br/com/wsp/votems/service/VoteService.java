package br.com.wsp.votems.service;

import br.com.wsp.votems.model.Vote;
import br.com.wsp.votems.model.repository.RulingRepository;
import br.com.wsp.votems.model.repository.VoteRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@Slf4j
public class VoteService {
    private final VoteRepository voteRepository;
    private final RulingRepository rulingRepository;

    public VoteService(VoteRepository voteRepository, RulingRepository rulingRepository) {
        this.voteRepository = voteRepository;
        this.rulingRepository = rulingRepository;
    }

    @KafkaListener(topics = "vote", groupId = "MSVote")
    public void exec(ConsumerRecord<String, String> registry) {

        String received = registry.value();
        log.info("Vote received = {}", received);

        ObjectMapper mapper = new ObjectMapper();
        Vote voto = null;


        try{
            voto = mapper.convertValue(received, Vote.class);
        } catch (Exception ex) {
            log.error("Falha ao converter voto [{}]", received, ex);
            return;
        }

        voto.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        Vote saved = voteRepository.save(voto);

        log.info("Vote registry sucess [id={}, date={}]", saved.getId(), saved.getCreateAt());
    }
}
