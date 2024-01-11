@Service
public class MatrixQuestionService {

    @Autowired
    private MatrixQuestionRepository matrixQuestionRepository;

    @Autowired
    private UserResponseRepository userResponseRepository;

    @Autowired
    private UserRepository userRepository;

    // With this method we will save or update a matrix question
    public MatrixQuestion saveOrUpdateMatrixQuestion(MatrixQuestion matrixQuestion) {
        return matrixQuestionRepository.save(matrixQuestion);
    }

    // Creates a new user if one does not exist (use the save method from UserRepository to do it), and then get the next question for that user
    public MatrixQuestion serveQuestion(UUID userUUID) {
        User user = userRepository.findByUuid(userUUID);
        if (user == null) {
            user = new User();
            user.setUuid(userUUID);
            userRepository.save(user);
        }

        MatrixQuestion question = matrixQuestionRepository.findUniqueQuestionForUser(user);
        if (question == null) {
            // Resets the list of questions the user has seen and get a new question
            userResponseRepository.deleteByUser(user);
            question = matrixQuestionRepository.findUniqueQuestionForUser(user);
        }
        return question;
    }

    // Method to capture a user's response to a question
    public UserResponse captureResponse(UserResponse response) {
        return userResponseRepository.save(response);
    }

}