package com.yoti.roombot.dataproviders.database.entitites;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("input_entity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InputEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Long requestId;

  @CreatedDate
  private LocalDateTime requestTime;

  @Column
  private int roomCoordsX;

  @Column
  private int roomCoordsY;

  @Column
  private int startX;

  @Column
  private int startY;

  @Column
  private String patchesCoords;

  @Column
  private int patchesCleaned;

}
