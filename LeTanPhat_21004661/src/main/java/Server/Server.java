/*
 * @ (#) Server.java     1.0   5/22/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package Server;

import dao.Dao_Candidate;
import dao.Dao_Certificate;
import dao.Dao_Experience;
import dao.Dao_Position;
import dao.impl.Impl_Candidate;
import dao.impl.Impl_Experience;
import dao.impl.Impl_Position;
import entity.Candidate;
import entity.Certificate;
import entity.Position;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 5/22/2024
 * @version:  1.0
 */
public class Server {
    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(7878)){
            System.out.println("Server is listening on port 7878");
            while (true) {
                Socket socket = server.accept();
                System.out.println("Client connected");
                System.out.println("Client address: " + socket.getInetAddress().getHostName());
                Thread t = new Thread(new ClientHandler(socket));
                t.start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable{
    private Socket socket;
    private Dao_Candidate candidateDAO;
    private Dao_Experience experienceDAO;
    private Dao_Position positionDAO;

    public ClientHandler(Socket socket) {
        super();
        this.socket = socket;
        candidateDAO = new Impl_Candidate();
        experienceDAO = new Impl_Experience();
        positionDAO = new Impl_Position();
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            int choice = 0;
            while(true) {
                choice = in.readInt();
                switch (choice) {
                    case 1:
                        String name = in.readUTF();
                        Double salaryFrom = in.readDouble();
                        Double salaryTo = in.readDouble();
                        List<Position> listPositions = positionDAO.listPositions(name, salaryFrom, salaryTo);
                        out.writeObject(listPositions);
                        break;
                    case 2:
                        Map<Candidate, Long> listCadidatesByCompanies = candidateDAO.listCadidatesByCompanies();
                        out.writeObject(listCadidatesByCompanies);
                        break;
                    case 3:
                        Map<Candidate, Long> listCandidatesWithLongestWorking = candidateDAO.listCandidatesWithLongestWorking();
                        out.writeObject(listCandidatesWithLongestWorking);
                        break;
                    case 4:
                        boolean isInserted = candidateDAO.addCandidate(new Candidate());
                        out.writeBoolean(isInserted);
                        break;
                    case 5:
                        String candidateID = in.readUTF();
                        Map<Position, Integer> listYearsOfExperrienceByPosition= experienceDAO.listYearsOfExperrienceByPosition(candidateID);
                        out.writeObject(listYearsOfExperrienceByPosition);
                        break;
                        case 6:
                            Map<Candidate, Set<Certificate>> listCandidatesWithCertificates = candidateDAO.listCadidatesAndCertificates();
                            out.writeObject(listCandidatesWithCertificates);
                            break;
                    case 7:
                        socket.close();
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
