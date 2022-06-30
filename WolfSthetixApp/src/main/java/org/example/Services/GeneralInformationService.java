package org.example.Services;

import org.example.Services.Models.GeneralInformation;
import org.example.Services.Models.Positions;
import org.example.repositories.GeneralInformationRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralInformationService {
    private final GeneralInformationRepository generalInformationRepository;

    public GeneralInformationService (GeneralInformationRepository generalInformationRepository) {
        this.generalInformationRepository = generalInformationRepository;
    }

    public GeneralInformation createUser(String nickname, String password, String email_address, String phone_number, String first_name, String last_name, String date_of_birth, Positions position) throws Exception {
        String requirements = passwordRequirements(password);
        String hashedPassword = sha256(password);
        return Mappers.fromGeneralInformationDAO(generalInformationRepository.createUser(nickname, hashedPassword, email_address, phone_number, first_name, last_name, date_of_birth, position));
    }

    public GeneralInformation getUser(int ID) {
        return Mappers.fromGeneralInformationDAO(generalInformationRepository.getUser(ID));
    }

    public List<GeneralInformation> listOfUsers (int page, int pageSize) {
        return generalInformationRepository.listOfUsers(page, pageSize)
                .stream()
                .map(Mappers::fromGeneralInformationDAO)
                .collect(Collectors.toList());
    }
    public void deleteUser (int ID) {
        generalInformationRepository.deleteUser(ID);
    }
    //Hashing Password:
    private static String sha256(String givenPassword) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        }catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception);
        }
        byte[] hash = messageDigest.digest(givenPassword.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for(byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append("0");
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    //Password Constraints:
    private static String passwordRequirements(String givenPassword) throws Exception {
        if (givenPassword.length()<=11) {
            throw new Exception("Your password must must contain more than 12 symbols");
        }
        else if (!givenPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$")) {
            throw new Exception("Your password must contain at least:\n" +
                                "One small letter,\n" +
                                "One CAPITAL letter,\n" +
                                "One number and\n" +
                                "One special symbol like: !,@,#,$,%,^,&,*");
        }
        else return givenPassword;
    }
}