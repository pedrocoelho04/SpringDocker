package com.lancer.compcon.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lancer.compcon.models.Frames;
import com.lancer.compcon.models.Stats;
import com.lancer.compcon.models.Trait;
import com.lancer.compcon.models.CoreSystem;
import com.lancer.compcon.models.ActiveSynergy;
import com.lancer.compcon.repository.FramesRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;


@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private FramesRepository framesRepository;

    @Override
    public void run(String... args) throws Exception {
        if (framesRepository.count() > 0) {
            System.out.println("O banco de dados já contém dados. Nenhuma ação necessária.");
            return;
        }

        System.out.println("Banco de dados vazio. Semeando dados iniciais para todos os frames...");

        List<Frames> framesToSave = new ArrayList<>();

        //================================================================================
        // General Massive Systems (GMS)
        //================================================================================

        // --- Frame: GMS-CHOMOLUNGMA ---
        Stats chom_stats = new Stats(1.0,1,8,0,10,10,10,6,8,15,0,10,4,0);
        Trait chom_t1 = new Trait("KEVLAR WEAVE","O mech pode se tornar imune a dano e efeitos de uma fonte por rodada como uma reação.");
        Trait chom_t2 = new Trait("REINFORCED CABLING","O mech pode ignorar o primeiro efeito de movimento forçado a cada rodada.");
        CoreSystem chom_cs = new CoreSystem("Karakoram-Class NHP","CHOMOLUNGMA PROTOCOL","Ao ativar, o mech ganha resistência a todos os danos até o final da cena. Além disso, uma vez por rodada, pode realizar um teste de SISTEMAS para reforçar um aliado, concedendo-lhe resistência a um tipo de dano por uma rodada.","1/missão","Ação Rápida",Collections.emptyList(), Arrays.asList("Flexível","Flexível","Flexível"));
        
        Frames chomFrame = new Frames();
        chomFrame.setFrames_id("gpc_chomolungma");
        chomFrame.setLicensed_level(0);
        chomFrame.setSource("GMS");
        chomFrame.setFrames_name("GMS-CHOMOLUNGMA");
        chomFrame.setMech_type(Arrays.asList("Defensor","Resistente"));
        chomFrame.setY_pos(0);
        chomFrame.setDescription("Chassi de linha de frente GMS projetado para máxima capacidade de sobrevivência e proteção de aliados.");
        chomFrame.setMounts(Arrays.asList("Principal","Flexível","Flexível"));
        chomFrame.setImage_url("https://exemplo.com/chomolungma.png");
        chomFrame.setLicensed_id("gms_standard_pattern_i");
        chomFrame.setStats(chom_stats);
        chomFrame.setTraits(Arrays.asList(chom_t1,chom_t2));
        chomFrame.setCore_systems(chom_cs);
        framesToSave.add(chomFrame);

        // --- Frame: GMS-EVEREST ---
        Stats eve_stats = new Stats(1.0,1,8,0,10,10,10,6,8,15,0,10,4,0);
        Trait eve_t1 = new Trait("INITIATIVE","No início do combate, pode se mover até sua velocidade.");
        Trait eve_t2 = new Trait("POWER SPIKE","Uma vez por rodada, pode adicionar +1d6 de dano bônus a um ataque.");
        CoreSystem eve_cs = new CoreSystem("Everest-Class NHP","CORE POWER","Ao ativar, o piloto pode escolher um dos três benefícios para a cena: +2 de dano em todos os ataques, +2 de VELOCIDADE e EVASÃO, ou limpar todo o CALOR e se tornar imune ao superaquecimento.","1/missão","Ação Rápida",Collections.emptyList(), Arrays.asList("Principal","Flexível","Flexível"));

        Frames eveFrame = new Frames();
        eveFrame.setFrames_id("gpc_everest");
        eveFrame.setLicensed_level(0);
        eveFrame.setSource("GMS");
        eveFrame.setFrames_name("GMS-EVEREST");
        eveFrame.setMech_type(Arrays.asList("Versátil","Balanceado"));
        eveFrame.setY_pos(0);
        eveFrame.setDescription("Chassi GMS padrão, confiável e adaptável a qualquer papel no campo de batalha.");
        eveFrame.setMounts(Arrays.asList("Principal","Flexível","Flexível"));
        eveFrame.setImage_url("https://exemplo.com/everest.png");
        eveFrame.setLicensed_id("gms_standard_pattern_i");
        eveFrame.setStats(eve_stats);
        eveFrame.setTraits(Arrays.asList(eve_t1,eve_t2));
        eveFrame.setCore_systems(eve_cs);
        framesToSave.add(eveFrame);

        // --- Frame: GMS-SAGARMATHA ---
        Stats sag_stats = new Stats(1.0,1,8,2,12,7,10,6,8,15,0,10,3,0);
        Trait sag_t1 = new Trait("HEAVY FRAME","O mech é imune a movimento forçado de fontes de tamanho igual ou menor.");
        Trait sag_t2 = new Trait("ANCHOR","Como uma ação rápida, pode se ancorar no lugar, tornando-se imobilizado, mas ganhando resistência a todos os danos de fontes à sua frente.");
        CoreSystem sag_cs = new CoreSystem("Tenzing-Class NHP","SAGARMATHA PROTOCOL","Ao ativar, o mech projeta um campo de força em um cone de 5. Aliados dentro do campo ganham cobertura pesada e resistência a danos de fontes fora do campo. O mech pode manter este campo como uma ação rápida em seus turnos.","1/missão","Ação Rápida",Collections.emptyList(), Arrays.asList("Pesado","Flexível"));
        
        Frames sagFrame = new Frames();
        sagFrame.setFrames_id("gpc_sagarmatha");
        sagFrame.setLicensed_level(0);
        sagFrame.setSource("GMS");
        sagFrame.setFrames_name("GMS-SAGARMATHA");
        sagFrame.setMech_type(Arrays.asList("Defensor","Âncora"));
        sagFrame.setY_pos(0);
        sagFrame.setDescription("Uma variante mais pesada do chassi padrão GMS, otimizado para ser uma fortaleza imóvel no campo de batalha.");
        sagFrame.setMounts(Arrays.asList("Pesado","Principal"));
        sagFrame.setImage_url("https://exemplo.com/sagarmatha.png");
        sagFrame.setLicensed_id("gms_standard_pattern_i");
        sagFrame.setStats(sag_stats);
        sagFrame.setTraits(Arrays.asList(sag_t1,sag_t2));
        sagFrame.setCore_systems(sag_cs);
        framesToSave.add(sagFrame);
        
        //================================================================================
        // IPS-Northstar (IPS-N)
        //================================================================================

        // --- Frame: IPS-N BLACKBEARD ---
        Stats bb_stats = new Stats(1.0,1,8,1,10,8,8,6,6,8,0,12,4,0);
        Trait bb_t1 = new Trait("GRAPPLING HOOKS","Pode puxar inimigos de tamanho igual ou menor para espaços adjacentes como uma ação rápida.");
        Trait bb_t2 = new Trait("REINFORCED GRAPPLE","Ao agarrar um inimigo, ele sofre 2 de dano cinético no início de cada um de seus turnos.");
        CoreSystem bb_cs = new CoreSystem("Chain-Class NHP","GRAPPLE AND PULL","O mech pode disparar até dois ganchos em alvos dentro do alcance do sensor. Os alvos devem ter sucesso em um teste de ENGENHARIA ou são puxados para espaços adjacentes ao mech e ficam imobilizados e agarrados.","1/missão","Ação Completa",Collections.emptyList(), Arrays.asList("Principal/Aux","Principal/Aux"));
        
        Frames bbFrame = new Frames();
        bbFrame.setFrames_id("ipsn_blackbeard");
        bbFrame.setLicensed_level(1);
        bbFrame.setSource("IPS-N");
        bbFrame.setFrames_name("IPS-N BLACKBEARD");
        bbFrame.setMech_type(Arrays.asList("Lutador","Controle"));
        bbFrame.setY_pos(0);
        bbFrame.setDescription("Um frame de combate corpo a corpo temível, especializado em agarrar e controlar o campo de batalha.");
        bbFrame.setMounts(Arrays.asList("Principal","Pesado"));
        bbFrame.setImage_url("https://exemplo.com/blackbeard.png");
        bbFrame.setLicensed_id("ipsn_license_i");
        bbFrame.setStats(bb_stats);
        bbFrame.setTraits(Arrays.asList(bb_t1,bb_t2));
        bbFrame.setCore_systems(bb_cs);
        framesToSave.add(bbFrame);

        // --- Frame: IPS-N DRAKE ---
        Stats drake_stats = new Stats(2.0,1,10,2,15,6,10,8,6,10,0,12,3,0);
        Trait drake_t1 = new Trait("HEAVY-DUTY FRAME","O mech tem +5 de HP máximo.");
        Trait drake_t2 = new Trait("ARGONAUT SHIELD","Pode usar um escudo pessoal para ganhar resistência a um ataque como uma reação, uma vez por rodada.");
        CoreSystem drake_cs = new CoreSystem("Leviathan-Class NHP","AEGIS PROTOCOL","Ao ativar, o mech cria um campo de proteção de rajada 2 centrado em si mesmo. Aliados dentro do campo ganham resistência a todos os danos. O campo dura até o final da cena.","1/missão","Ação Completa",Collections.emptyList(), Arrays.asList("Principal","Pesado"));
        
        Frames drakeFrame = new Frames();
        drakeFrame.setFrames_id("ipsn_drake");
        drakeFrame.setLicensed_level(1);
        drakeFrame.setSource("IPS-N");
        drakeFrame.setFrames_name("IPS-N DRAKE");
        drakeFrame.setMech_type(Arrays.asList("Defensor","Tanque"));
        drakeFrame.setY_pos(0);
        drakeFrame.setDescription("A personificação da durabilidade, o Drake é uma muralha de aço móvel projetada para suportar punições incríveis.");
        drakeFrame.setMounts(Arrays.asList("Pesado","Pesado"));
        drakeFrame.setImage_url("https://exemplo.com/drake.png");
        drakeFrame.setLicensed_id("ipsn_license_i");
        drakeFrame.setStats(drake_stats);
        drakeFrame.setTraits(Arrays.asList(drake_t1,drake_t2));
        drakeFrame.setCore_systems(drake_cs);
        framesToSave.add(drakeFrame);
        
        //================================================================================
        // Smith-Shimano Corpro (SSC)
        //================================================================================
        
        // --- Frame: SSC BLACK WITCH ---
        Stats bw_stats = new Stats(1.0,1,6,0,8,14,12,6,8,15,2,8,5,0);
        Trait bw_t1 = new Trait("FERROUS LESSON","Pode mover objetos metálicos de tamanho 1 ou menor dentro do alcance do sensor como uma ação rápida.");
        Trait bw_t2 = new Trait("PRECOGNITION","O primeiro ataque contra este mech a cada rodada tem desvantagem.");
        CoreSystem bw_cs = new CoreSystem("Oracle-Class NHP","FATE REWEAVE","Ao ativar, até o final da cena, o piloto pode forçar um inimigo a rolar novamente qualquer teste de ataque, salvamento ou habilidade, ou permitir que um aliado role novamente. Esta habilidade pode ser usada uma vez por rodada.","1/missão","Ação Rápida",Collections.emptyList(), Arrays.asList("Principal","Principal"));

        Frames bwFrame = new Frames();
        bwFrame.setFrames_id("ssc_black_witch");
        bwFrame.setLicensed_level(1);
        bwFrame.setSource("SSC");
        bwFrame.setFrames_name("SSC BLACK WITCH");
        bwFrame.setMech_type(Arrays.asList("Controle","Psíquico"));
        bwFrame.setY_pos(0);
        bwFrame.setDescription("Um chassi misterioso que parece dobrar a realidade ao seu redor, especializado em controle e manipulação do campo de batalha.");
        bwFrame.setMounts(Arrays.asList("Principal","Principal"));
        bwFrame.setImage_url("https://exemplo.com/blackwitch.png");
        bwFrame.setLicensed_id("ssc_license_i");
        bwFrame.setStats(bw_stats);
        bwFrame.setTraits(Arrays.asList(bw_t1,bw_t2));
        bwFrame.setCore_systems(bw_cs);
        framesToSave.add(bwFrame);

        // --- Frame: SSC DEATH'S HEAD ---
        Stats dh_stats = new Stats(0.5,1,6,0,6,14,8,8,6,20,-2,8,6,0);
        Trait dh_t1 = new Trait("HUNTER'S MARK","Pode marcar um alvo como uma ação rápida. Todos os ataques contra o alvo marcado ganham +1 de precisão.");
        Trait dh_t2 = new Trait("STEALTH FIELD","O mech é invisível para sensores enquanto não estiver atacando ou realizando ações hostis.");
        CoreSystem dh_cs = new CoreSystem("Executioner-Class NHP","FINAL HUNT","Ao ativar, o piloto marca um alvo. Por uma rodada, todos os ataques feitos por este mech contra o alvo são acertos críticos automáticos. O mech não pode atacar outros alvos nesta rodada.","1/missão","Ação Rápida",Collections.emptyList(), Arrays.asList("Principal/Aux","Principal/Aux"));
        
        Frames dhFrame = new Frames();
        dhFrame.setFrames_id("ssc_deaths_head");
        dhFrame.setLicensed_level(1);
        dhFrame.setSource("SSC");
        dhFrame.setFrames_name("SSC DEATH'S HEAD");
        dhFrame.setMech_type(Arrays.asList("Atirador de Elite","Furtivo"));
        dhFrame.setY_pos(0);
        dhFrame.setDescription("Um predador de elite, o Death's Head é projetado para eliminar alvos de alta prioridade a longas distâncias sem ser detectado.");
        dhFrame.setMounts(Arrays.asList("Pesado","Aux/Aux"));
        dhFrame.setImage_url("https://exemplo.com/deathshead.png");
        dhFrame.setLicensed_id("ssc_license_i");
        dhFrame.setStats(dh_stats);
        dhFrame.setTraits(Arrays.asList(dh_t1,dh_t2));
        dhFrame.setCore_systems(dh_cs);
        framesToSave.add(dhFrame);

        //================================================================================
        // HORUS
        //================================================================================

        // --- Frame: HORUS GOBLIN ---
        Stats gob_stats = new Stats(0.5,1,6,0,6,12,14,10,8,10,4,8,5,0);
        Trait gob_t1 = new Trait("PUPPETMASTER","Pode forçar um mech inimigo a fazer um ataque básico contra um alvo de sua escolha (incluindo a si mesmo) como uma ação completa, se ele falhar em um teste de SISTEMAS.");
        Trait gob_t2 = new Trait("GHOSTWEAVE","O mech pode se tornar invisível para um único alvo por uma rodada como uma ação rápida.");
        CoreSystem gob_cs = new CoreSystem("Sisyphus-Class NHP","HACK THE PLANET","Ao ativar, o piloto pode escolher até 3 alvos dentro do alcance do sensor. Cada alvo sofre um efeito de invasão massivo, como superaquecimento, imobilização ou desarmamento, sem a necessidade de um teste de ataque tecnológico.","1/missão","Ação Completa",Collections.emptyList(), Arrays.asList("Principal/Aux"));
        
        Frames gobFrame = new Frames();
        gobFrame.setFrames_id("horus_goblin");
        gobFrame.setLicensed_level(1);
        gobFrame.setSource("HORUS");
        gobFrame.setFrames_name("HORUS GOBLIN");
        gobFrame.setMech_type(Arrays.asList("Hacker","Controle"));
        gobFrame.setY_pos(0);
        gobFrame.setDescription("Um mestre da guerra eletrônica, o Goblin se destaca em virar a tecnologia inimiga contra eles.");
        gobFrame.setMounts(Arrays.asList("Principal","Aux/Aux"));
        gobFrame.setImage_url("https://exemplo.com/goblin.png");
        gobFrame.setLicensed_id("horus_license_i");
        gobFrame.setStats(gob_stats);
        gobFrame.setTraits(Arrays.asList(gob_t1,gob_t2));
        gobFrame.setCore_systems(gob_cs);
        framesToSave.add(gobFrame);
        
        //================================================================================
        // Harrison Armory (HA)
        //================================================================================

        // --- Frame: HA BARBAROSSA ---
        Stats bar_stats = new Stats(3.0,1,10,3,18,5,10,12,6,8,0,14,2,0);
        Trait bar_t1 = new Trait("SIEGE ARMOR","O mech tem resistência a danos de explosão e de área.");
        Trait bar_t2 = new Trait("STABILIZER JETS","O mech não sofre penalidades por disparar armas superpesadas ou de cerco.");
        CoreSystem bar_cs = new CoreSystem("Genghis-Class NHP","APOCALYPSE RAIL","O mech dispara um tiro massivo em linha reta que ignora cobertura e causa 4d6 de dano explosivo a todos em seu caminho. O terreno na linha de fogo é destruído.","1/missão","Ação Completa",Collections.emptyList(), Arrays.asList("Superpesado"));
        
        Frames barFrame = new Frames();
        barFrame.setFrames_id("ha_barbarossa");
        barFrame.setLicensed_level(1);
        barFrame.setSource("HA");
        barFrame.setFrames_name("HA BARBAROSSA");
        barFrame.setMech_type(Arrays.asList("Artilharia","Cerco"));
        barFrame.setY_pos(0);
        barFrame.setDescription("Uma plataforma de armas móvel, o Barbarossa foi projetado para uma coisa: poder de fogo avassalador.");
        barFrame.setMounts(Arrays.asList("Principal","Superpesado"));
        barFrame.setImage_url("https://exemplo.com/barbarossa.png");
        barFrame.setLicensed_id("ha_license_i");
        barFrame.setStats(bar_stats);
        barFrame.setTraits(Arrays.asList(bar_t1,bar_t2));
        barFrame.setCore_systems(bar_cs);
        framesToSave.add(barFrame);

        // --- Frame: GMS-GENGHIS ---
        Stats genghisStats = new Stats(1.0,1,6,0,10,8,8,8,6,10,0,10,4,0);
        Trait explosiveShunt = new Trait("Explosive Shunt", "Quando você é destruído, você explode em uma explosão 2. Todos os personagens na área devem ter sucesso em um teste de Agilidade ou sofrer 2d6 de dano explosivo.");
        Trait autoCooler = new Trait("Auto-Cooler", "No final de cada um dos seus turnos, limpe 1 Calor se você não tiver se movido mais do que sua velocidade.");
        ActiveSynergy luciferno = new ActiveSynergy(Arrays.asList("Ataque", "Arma de Calor", "Explosão 1"), "Você causa +1 de dano de calor adicional em todos os ataques que não são superpesados.");
        CoreSystem genghisCore = new CoreSystem("Lucifer-Class NHP", "Total System Meltdown", "Imediatamente após usar o Sistema Principal, você pode se mover até sua velocidade e fazer um ataque com uma única arma. Este ataque causa +1d6 de dano de bônus.", "1/missão", "Ação Rápida", Collections.singletonList(luciferno), Arrays.asList("Aux/Aux", "Flexível", "Principal/Aux"));

        Frames genghisFrame = new Frames();
        genghisFrame.setFrames_id("gpc_genghis");
        genghisFrame.setLicensed_level(1);
        genghisFrame.setSource("GMS");
        genghisFrame.setFrames_name("GMS-GENGHIS");
        genghisFrame.setMech_type(Arrays.asList("Caçador", "Artilheiro"));
        genghisFrame.setY_pos(0);
        genghisFrame.setDescription("Um chassi de artilharia de curto alcance projetado para combate corpo a corpo e negação de área.");
        genghisFrame.setMounts(Arrays.asList("Principal", "Pesado"));
        genghisFrame.setImage_url("https://exemplo.com/genghis.png");
        genghisFrame.setLicensed_id("gms_standard_pattern_i");
        genghisFrame.setStats(genghisStats);
        genghisFrame.setTraits(Arrays.asList(explosiveShunt, autoCooler));
        genghisFrame.setCore_systems(genghisCore);
        framesToSave.add(genghisFrame);
        
        // Adicione mais frames de HA aqui...
        // Ex: GENGHIS, ISKANDER, NAPOLEON, SHERMAN, TOKUGAWA

        // Salva todos os frames criados no banco de dados de uma só vez
        framesRepository.saveAll(framesToSave);
        
        System.out.println("Dados iniciais semeados com sucesso. Total de frames: " + framesRepository.count());
    }
}
