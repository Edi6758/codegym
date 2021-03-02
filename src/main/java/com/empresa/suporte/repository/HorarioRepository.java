package com.empresa.suporte.repository;

import com.empresa.suporte.model.Horario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    public List<Horario> findBySemana(String semana);

    @Query(value=" SELECT h.id, h.hora, h.limite, h.semana "
    		+ "    FROM horario h"
    		+ "    WHERE h.semana = ?1 "
    		+ "    AND h.limite > ("
    		+ "     	SELECT COUNT(u.usuario_id)"
    		+ "	    	FROM horario ho "
    		+ "			LEFT JOIN usuario_horario u ON u.horario_id = ho.id "
    		+ "     	WHERE ho.semana = ?1 AND ho.id = h.id)"
    		+ "	  ORDER BY h.hora", nativeQuery=true)
    public List<Horario> findBySemanaAndLimite(String semana);
    
    @Query(value=" SELECT h.id, h.hora, h.limite, h.semana "
    		+ "    FROM horario h"
    		+ "    WHERE h.semana = ?1 "
    		+ "    AND h.limite > ("
    		+ "     	SELECT COUNT(u.usuario_id)"
    		+ "	    	FROM horario ho "
    		+ "			LEFT JOIN usuario_horario u ON u.horario_id = ho.id "
    		+ "     	WHERE ho.semana = ?1 AND ho.id = h.id AND u.usuario_id <> ?2)"
    		+ "	  ORDER BY h.hora", nativeQuery=true)
    public List<Horario> findBySemanaAndLimite(String semana, long usuario_id);
}
