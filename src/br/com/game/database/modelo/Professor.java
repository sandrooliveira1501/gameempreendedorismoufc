package br.com.game.database.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 
 * @author alexsandro
 *
 *	O login do Usuario é representado pelo número SIAPE do Professor
 *
 */
@Entity
public class Professor extends Usuario{

}
