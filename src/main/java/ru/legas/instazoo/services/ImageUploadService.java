package ru.legas.instazoo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.legas.instazoo.entity.User;
import ru.legas.instazoo.repositories.ImageRepository;
import ru.legas.instazoo.repositories.PostRepository;
import ru.legas.instazoo.repositories.UserRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.zip.Deflater;

@Service
public class ImageUploadService {
    public static final Logger LOG = LoggerFactory.getLogger(ImageUploadService.class);

    private ImageRepository imageRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public ImageUploadService(ImageRepository imageRepository,
                              PostRepository postRepository,
                              UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    private byte[] compressBytes(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()){
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try{
            outputStream.close();
        } catch (IOException e){
            LOG.error("Cannot compress Bytes");
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }


    private User getUserByPrincipal(Principal principal){
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }
}
