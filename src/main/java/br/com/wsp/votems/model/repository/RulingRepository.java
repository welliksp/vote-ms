package br.com.wsp.votems.model.repository;

import br.com.wsp.votems.model.Ruling;
import br.com.wsp.votems.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RulingRepository extends JpaRepository<Ruling, Long> {
}
