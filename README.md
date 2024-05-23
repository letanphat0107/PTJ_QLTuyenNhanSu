/*
 * @ (#) Client.java     1.0   5/22/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package client;

import entity.Candidate;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * @description:
 * @author: Le Tan Phat
 * @code: 21004661
 * @date: 5/22/2024
 * @version:  1.0
 */
public class Client {
    public static void main(String[] args) {

        try(Socket socket = new Socket("192.168.1.8", 7878);

            Scanner sc = new Scanner(System.in);
        ){

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            int choice = 0;

            while(true) {

                System.out.println("Enter your choice: \n"
                        + "1. List position\n"
                        + "2. List candidate\n"
                + "3. List candidate long\n"
                        + "4. Add candidates\n"
                        + "5. Add listYearsOfExperrienceByPosition\n"
                + "6. Add listCandidatesWithCertificates\n");

                choice  = sc.nextInt();

                out.writeInt(choice);

                switch (choice) {
                    case 1:
                        sc.nextLine();
//                        System.out.println("Enter the course title: ");
//                        String title = sc.nextLine();
//
//                        out.writeUTF(title);
//                        out.flush();
//
////					Receive the results from the server
//
//                        List<Student> students = (List<Student>) in.readObject();
//                        students.forEach(System.out::println);

                        break;

                    case 2:
                        Map<Candidate, Long> listCadidatesByCompanies = (Map<Candidate, Long>) in.readObject();
                        listCadidatesByCompanies.forEach((k, v) -> System.out.println(k + " - " + v));

                        break;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
