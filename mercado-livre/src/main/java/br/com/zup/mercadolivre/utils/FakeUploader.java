package br.com.zup.mercadolivre.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class FakeUploader {

    public List<String> envia(List<MultipartFile> imagens){
        return imagens.stream().map(imagem-> "http://s3/mercadolivre/"+imagem.getOriginalFilename()+"-"+ UUID.randomUUID().toString())
                .collect(Collectors.toList());
    }
}
