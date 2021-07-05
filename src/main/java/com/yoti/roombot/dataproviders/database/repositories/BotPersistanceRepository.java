package com.yoti.roombot.dataproviders.database.repositories;

import com.yoti.roombot.dataproviders.database.entitites.InputEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotPersistanceRepository extends JpaRepository<InputEntity, Long> {

}
