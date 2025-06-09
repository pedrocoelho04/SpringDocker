    package com.lancer.compcon.repository;

    import com.lancer.compcon.models.Trait;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;
    import java.util.List;

    /**
    * Repositório para a entidade Trait.
    * Os métodos de consulta personalizados foram reescritos com a anotação @Query
    * para garantir a interpretação correta dos nomes de campo e evitar erros de parsing.
    */
    @Repository
    public interface TraitRepository extends JpaRepository<Trait, Long> {

        /**
        * Usa uma consulta JPQL explícita para encontrar Traits pelo seu nome.
        * @param traits_name O nome do trait a ser pesquisado.
        * @return Uma lista de traits que correspondem ao nome.
        */
        @Query("SELECT t FROM Trait t WHERE t.traits_name = :traits_name")
        List<Trait> findByTraits_name(@Param("traits_name") String traits_name);

        /**
        * Usa uma consulta JPQL explícita para encontrar todos os Traits associados a um Frame específico,
        * navegando através da relação 'frame' e filtrando pelo 'frames_id'.
        * @param framesId O ID do Frame cujos traits você quer encontrar.
        * @return Uma lista de traits associados ao Frame.
        */
        @Query("SELECT t FROM Trait t WHERE t.frame.frames_id = :framesId")
        List<Trait> findByFrame_Frames_id(@Param("framesId") String framesId);

    }
