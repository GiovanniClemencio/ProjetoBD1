/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Classes.Campanha;
import Classes.ItemDTO;
import Classes.Jogador;
import Classes.Missao;
import Classes.Personagem;
import DAO.CampanhaDAO;
import DAO.MissaoDAO;
import DAO.PersonagemDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class ControladorCampanha implements Controlador {

    private final CampanhaDAO campanhaDAO;
    private final MissaoDAO missaoDAO;
    private final PersonagemDAO personagemDAO;

    public ControladorCampanha() {
        this.campanhaDAO = new CampanhaDAO();
        this.missaoDAO = new MissaoDAO();
        this.personagemDAO = new PersonagemDAO();
    }

    // Cadastrar
    public void cadastrarCampanha(String nome, String idMestre) throws SQLException, IllegalArgumentException {

        // Validar nome
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da campanha não pode estar vazio!");
        }
        // Validar mestre
        if (idMestre == null || idMestre.isBlank()) {
            throw new IllegalArgumentException("Campanha precisa de um mestre cadastrado!");
        }

        Campanha campanha = new Campanha(nome, idMestre);

        campanhaDAO.inserir(campanha);
    }

    // Excluir campanha
    public void excluirCampanha(String idCampanha) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID inválido para exclusão da campanha.");
        }

        campanhaDAO.excluir(idCampanha);
    }

    // Atualizar campanha
    public void atualizarCampanha(String idCampanha, String nome, String idMestre) throws SQLException, IllegalArgumentException {

        // Validar
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID inválido para atualização da campanha.");
        }
        if(nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da campanha não pode estar vazio!");
        }
        if (idMestre == null || idMestre.isBlank()) {
            throw new IllegalArgumentException("A campanha deve ter um mestre!");
        }

        Campanha campanhaAtualizada = new Campanha(nome, idMestre);
        campanhaAtualizada.setIdCampanha(idCampanha);

        campanhaDAO.atualizar(campanhaAtualizada);
    }

    // Buscar por ID
    public Campanha buscarCampanha(String idCampanha) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID inválido para busca de campanha.");
        }

        return campanhaDAO.buscarPorId(idCampanha);
    }

    // Buscar personagens que participam da campanha
    public ArrayList<Personagem> obterPersonagensParticipantes(String idCampanha) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID da campanha inválido para listagem de participantes.");
        }

        return campanhaDAO.listarPersonagensParticipantes(idCampanha);
    }

    // Vinculando um personagem a uma campanha
    public void vincularPersonagem(String idCampanha, String idPersonagem) throws SQLException, IllegalArgumentException {
        // Validação
        if (idCampanha == null || idCampanha.isBlank() || idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para realizar o vínculo.");
        }

        campanhaDAO.adicionarPersonagem(idCampanha, idPersonagem);
    }

    // Desvinculando um personagem a uma campanha
    public void desvincularPersonagem(String idCampanha, String idPersonagem) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank() || idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para remover o personagem da campanha.");
        }

        campanhaDAO.removerPersonagem(idCampanha, idPersonagem);
    }

    // Vincular missão a campanha
    public void adicionarMissaoACampanha(String idCampanha, String idMissao) throws SQLException, IllegalArgumentException {
        
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID da campanha inválido.");
        }
        if (idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("ID da missão inválido.");
        }

        campanhaDAO.adicionarMissaoAcampanha(idCampanha, idMissao);
    }

    // Remover missão da campanha
    public void removerMissaoDaCampanha(String idCampanha, String idMissao) throws SQLException, IllegalArgumentException {
        
        if (idCampanha == null || idCampanha.isBlank() || idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para desvincular a missão.");
        }

        campanhaDAO.removerMissaoDaCampanha(idCampanha, idMissao);
    }

    // Listar todos os jogadores únicos que possuem personagem na campanha
    public ArrayList<Jogador> listarJogadoresDaCampanha(String idCampanha) throws SQLException, IllegalArgumentException {
        
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID da campanha inválido para listagem de jogadores.");
        }

        return campanhaDAO.listarJogadoresPorCampanha(idCampanha);
    }

    // Listar todas as campanhas cadastradas
    public ArrayList<Campanha> listarTodasAsCampanhas() throws SQLException {
        return campanhaDAO.listarTodos();
    }
    
    // Listar todas as missões vinculadas a campanha
    public ArrayList<Missao> listarMissoesDaCampanha(String idCampanha) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank()) {
            throw new IllegalArgumentException("ID da campanha inválido para listagem de missões.");
        }

        return campanhaDAO.listarMissoesPorCampanha(idCampanha);
    }

    // Vincular participante a missão
    public void adicionarParticipanteAMissao(String idCampanha, String idMissao, String idPersonagem) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank() || idMissao == null || idMissao.isBlank() || idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para adicionar participante.");
        }
        campanhaDAO.adicionarPersonagemAMissao(idCampanha, idMissao, idPersonagem);
    }

    // Desvincular participante da missão
    public void desvincularPersonagemDaMissao(String idCampanha, String idMissao, String idPersonagem) throws SQLException, IllegalArgumentException {
        if (idCampanha == null || idCampanha.isBlank() || 
            idMissao == null || idMissao.isBlank() || 
            idPersonagem == null || idPersonagem.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para remover o personagem da missão.");
        }

        campanhaDAO.removerPersonagemDaMissao(idCampanha, idMissao, idPersonagem);
    }

    // Listar personagens participantes da missão em uma campanha
    public ArrayList<Personagem> listarParticipantesDaMissao(String idCampanha, String idMissao) throws SQLException, IllegalArgumentException {

        if (idCampanha == null || idCampanha.isBlank() || idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("IDs inválidos para listagem");
        }

        return campanhaDAO.listarParticipantesDaMissao(idCampanha, idMissao);
    }

    // Finalizar uma missão, distribuindo o XP e os itens para todos os personagens participantes
    public void concluirMissao(String idCampanha, String idMissao) throws SQLException, IllegalArgumentException {
        
        if (idCampanha == null || idCampanha.isBlank() ||idMissao == null || idMissao.isBlank()) {
            throw new IllegalArgumentException("ID inválido para conclusão.");
        }

        int xpGanho = missaoDAO.buscarXpBonusDaMissao(idMissao);

        ArrayList<Personagem> participantes = campanhaDAO.listarParticipantesDaMissao(idCampanha, idMissao);

        if (participantes.isEmpty()) {
            throw new IllegalArgumentException("Não é possível concluir uma missão sem personagens participantes!");
        }

        ArrayList<ItemDTO> recompensas = missaoDAO.listarRecompensasDaMissao(idMissao);


        for (Personagem personagem : participantes) {
            String idPersonagem = personagem.getIdPersonagem();
            
            personagemDAO.adicionarXP(idPersonagem, xpGanho);
            
            for (ItemDTO item : recompensas) {
                personagemDAO.adquirirItem(idPersonagem, item.getIdItem(), item.getQuantidade());
            }
        }

        campanhaDAO.atualizarStatusMissao(idCampanha, idMissao, true);
    }
}
