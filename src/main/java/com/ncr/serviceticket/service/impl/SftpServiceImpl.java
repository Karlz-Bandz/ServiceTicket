package com.ncr.serviceticket.service.impl;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.ncr.serviceticket.dto.SftpDto;
import com.ncr.serviceticket.service.SftpService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@Service
public class SftpServiceImpl implements SftpService {

    private Session session;

    private ChannelSftp channelSftp;

    private final Logger logger = Logger.getLogger(SftpServiceImpl.class.getName());

    private final String home = System.getProperty("user.home");

    @Override
    public void restart(String system) throws SftpException {

        if (session != null && channelSftp != null) {
            final File destList = new File(home + "/update/xp/restart/destlist.txt");
            final File fileList = new File(home + "/update/xp/restart/filelist.txt");

            try (InputStream destStream = new FileInputStream(destList);
                 InputStream fileStream = new FileInputStream(fileList)) {

                channelSftp.put(destStream, destList.getName());
                channelSftp.put(fileStream, fileList.getName());

            } catch (IOException e) {
                logger.info("Stream Error");
            }
        } else {
            logger.info("Not connected!");
        }
    }

    @Override
    public void connect(SftpDto ftpDto) throws JSchException {
        final String known_hosts = home + "/.ssh/known_hosts";

        final JSch jSch = new JSch();

        jSch.setKnownHosts(known_hosts);

        session = jSch.getSession(ftpDto.userName(), ftpDto.hostName(), ftpDto.port());
        session.setPassword(ftpDto.pass());
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        channelSftp = (ChannelSftp) channel;
    }

    @Override
    public void disconnect() {
        if (session != null && channelSftp != null) {
            session.disconnect();
            channelSftp.disconnect();
        }
    }
}
