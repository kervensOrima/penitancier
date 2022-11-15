package com.ht.penitancier.utils;

import com.ht.penitancier.dtos.DetenuDTO;
import com.ht.penitancier.dtos.PrisonDTO;
import com.ht.penitancier.models.Detenu;
import com.ht.penitancier.models.Prison;
import org.springframework.beans.BeanUtils;

public class ObjectMapper {

    public static Detenu getDetenu(DetenuDTO request) {
        Detenu detenu = new Detenu();
        BeanUtils.copyProperties(request, detenu);
        detenu.setPrison(getPrison(request.getPrison()));
        detenu.setCode(request.getCode());
        return detenu;
    }

    public static DetenuDTO getDetenuDTO(Detenu request) {
        DetenuDTO detenuDTO = new DetenuDTO() ;
        BeanUtils.copyProperties(request, detenuDTO);
        detenuDTO.setPrison(getPrisonDTO(request.getPrison()));
        detenuDTO.setCode(request.getCode());
        return detenuDTO;
    }

    public static PrisonDTO getPrisonDTO(Prison request) {
        PrisonDTO dto = new PrisonDTO() ;
        BeanUtils.copyProperties(request, dto);
        return dto;
    }

    public static Prison getPrison(PrisonDTO request) {
        Prison prison = new Prison() ;
        BeanUtils.copyProperties(request, prison);
        return prison;
    }
}
