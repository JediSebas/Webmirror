package com.jedisebas.webmirror;

import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FTPConfig {

    private DefaultFtpSessionFactory ftpSessionFactory() {
        DefaultFtpSessionFactory factory = new DefaultFtpSessionFactory();
        factory.setHost(UserController.ip);
        factory.setPort(21);
        factory.setUsername("user");
        factory.setPassword("zaq1@WSX");
        return factory;
    }

    public void upload() {
        FtpSession session = ftpSessionFactory().getSession();
        InputStream resourceAsStream =
                FTPConfig.class.getClassLoader().getResourceAsStream("/opt/lampp/htdocs/mirror/Sebastian_Dolata_1_00001.jpg");

        try {
            session.write(resourceAsStream, "/IntelligentMirror/data/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        session.close();
    }

    public String download(String picture_name) {
        FtpSession session = ftpSessionFactory().getSession();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            session.read("/var/www/html/mirror/" + picture_name, outputStream);
            return outputStream.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
