package br.com.desafiojsf.controller;

import br.com.desafiojsf.model.Tarefa;
import br.com.desafiojsf.model.dao.InterfaceDao;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author decio
 */
@ManagedBean
@RequestScoped
public class TarefaController implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Tarefa tarefa;
    private Tarefa tarefaSelecionada;
    private List<Tarefa> tarefas;
    private Boolean flagEdicao;

    @EJB
    private InterfaceDao<Tarefa> dao;

    public TarefaController() {
         
    }

    @PostConstruct
    public void init() {        
        try {            
            this.tarefa = new Tarefa();
            this.tarefas = this.dao.getBeans(Tarefa.class);
        } catch (Exception ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public Tarefa getTarefaSelecionada() {
        return tarefaSelecionada;
    }

    public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
        this.tarefaSelecionada = tarefaSelecionada;
    }        

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public Boolean getFlagEdicao() {
        return flagEdicao;
    }

    public void setFlagEdicao(Boolean flagEdicao) {
        this.flagEdicao = flagEdicao;
    }

    public void setTarefaSelecionada() {
        this.tarefa = this.tarefaSelecionada;
    }

    public void insert() {
        if (this.tarefa.getDescricao() != null )  {
            try {
                this.tarefa.setRealizado(false);
                this.dao.merge(this.tarefa);      
                this.tarefas = this.dao.getBeans(Tarefa.class);
            } catch (Exception ex) {
                Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(Tarefa t) {
        this.tarefaSelecionada = t;           
    }

    public void save() {
        if (this.tarefaSelecionada.getDescricao() != null )  {
            try {                
                this.dao.merge(this.tarefaSelecionada);      
                this.tarefas = this.dao.getBeans(Tarefa.class);
                this.tarefaSelecionada = null;
            } catch (Exception ex) {
                Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void delete(Integer id) {
        try {
            this.dao.excluir(this.tarefaSelecionada);
        } catch (Exception ex) {
            Logger.getLogger(TarefaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
