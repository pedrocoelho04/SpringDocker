    package com.lancer.compcon.repository;

    import com.lancer.compcon.models.Trait;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;
    import java.util.List;

    @Repository
    public interface TraitRepository extends JpaRepository<Trait, Long> {

        @Query("SELECT t FROM Trait t WHERE t.traits_name = :traits_name")
        List<Trait> findByTraits_name(@Param("traits_name") String traits_name);

        @Query("SELECT t FROM Trait t WHERE t.frame.frames_id = :framesId")
        List<Trait> findByFrame_Frames_id(@Param("framesId") String framesId);
    }
