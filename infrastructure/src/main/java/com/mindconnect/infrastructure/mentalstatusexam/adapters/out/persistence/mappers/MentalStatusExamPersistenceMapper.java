package com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.mentalstatusexam.model.aggregates.MentalStatusExam;
import com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.entity.MentalStatusExamJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de mental_status_exams.
 */
@Component
public class MentalStatusExamPersistenceMapper {

    public MentalStatusExamJpaEntity toEntity(MentalStatusExam mentalStatusExam) {
        MentalStatusExamJpaEntity entity = new MentalStatusExamJpaEntity();
        entity.setId(mentalStatusExam.id());
        entity.setEncounterId(mentalStatusExam.encounterId());
        entity.setAppearance(mentalStatusExam.appearance());
        entity.setBehavior(mentalStatusExam.behavior());
        entity.setAttitude(mentalStatusExam.attitude());
        entity.setConsciousness(mentalStatusExam.consciousness());
        entity.setOrientation(mentalStatusExam.orientation());
        entity.setAttention(mentalStatusExam.attention());
        entity.setMemory(mentalStatusExam.memory());
        entity.setSpeech(mentalStatusExam.speech());
        entity.setMood(mentalStatusExam.mood());
        entity.setAffect(mentalStatusExam.affect());
        entity.setThoughtProcess(mentalStatusExam.thoughtProcess());
        entity.setThoughtContent(mentalStatusExam.thoughtContent());
        entity.setPerception(mentalStatusExam.perception());
        entity.setJudgment(mentalStatusExam.judgment());
        entity.setInsight(mentalStatusExam.insight());
        entity.setPsychomotorActivity(mentalStatusExam.psychomotorActivity());
        entity.setObservations(mentalStatusExam.observations());
        entity.setCreatedAt(mentalStatusExam.createdAt());
        entity.setCreatedBy(mentalStatusExam.createdBy());
        return entity;
    }

    public MentalStatusExam toDomain(MentalStatusExamJpaEntity entity) {
        return new MentalStatusExam(
                entity.getId(),
                entity.getEncounterId(),
                entity.getAppearance(),
                entity.getBehavior(),
                entity.getAttitude(),
                entity.getConsciousness(),
                entity.getOrientation(),
                entity.getAttention(),
                entity.getMemory(),
                entity.getSpeech(),
                entity.getMood(),
                entity.getAffect(),
                entity.getThoughtProcess(),
                entity.getThoughtContent(),
                entity.getPerception(),
                entity.getJudgment(),
                entity.getInsight(),
                entity.getPsychomotorActivity(),
                entity.getObservations(),
                entity.getCreatedAt(),
                entity.getCreatedBy());
    }
}
