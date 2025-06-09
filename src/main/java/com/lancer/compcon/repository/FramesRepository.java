package com.lancer.compcon.repository;

import com.lancer.compcon.models.Frames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FramesRepository extends JpaRepository<Frames, Long> {

    //Adicionado esse @Query porque sem eles não funciona o codigo...

    @Query("SELECT f FROM Frames f WHERE f.frames_name = :frames_name")
    List<Frames> findByFrames_name(@Param("frames_name") String frames_name);

    @Query("SELECT f FROM Frames f WHERE f.licensed_level = :licensed_level")
    List<Frames> findByLicensed_level(@Param("licensed_level") Integer licensed_level);

    @Query("SELECT f FROM Frames f WHERE :mech_type MEMBER OF f.mech_type")
    List<Frames> findByMech_typeContaining(@Param("mech_type") String mech_type);

    @Query("SELECT f FROM Frames f WHERE f.frames_id = :frames_id")
    Optional<Frames> findByFrames_id(@Param("frames_id") String frames_id);
}