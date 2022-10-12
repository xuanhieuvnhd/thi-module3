package dao;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static connectJDBC.ConnectJDBC.getConnection;

public class StudentDAO implements IStudentDAO{
    private static final String INSERT_STUDENT_SQL = "INSERT INTO Classroom (`name`,dateOfBirth,address,phoneNumber,email,classRoom) VALUES (?,?,?,?,?,?);";
    private static final String SELECT_STUDENT_BY_ID = "select id,`name`,dateOfBirth,address,phoneNumber,email,classRoom from Classroom where id=?";
    private static final String SELECT_ALL_STUDENT = "select * from Classroom";
    private static final String DELETE_STUDENT_SQL = "delete from Classroom where id = ?;";
    private static final String UPDATE_STUDENT_SQL = "update Classroom set `name` = ?,dateOfBirth=?,address=?,phoneNumber= ?,email=?,classRoom=? where id = ?;";
    private static final String SEARCH_STUDENT_BY_NAME = "select * from Classroom where `name` like ?";
    private static final String SORT_STUDENT_BY_NAME = "select * from Classroom order by `name`";
    public StudentDAO() {
    }
    public void insertStudent(Student student) {
        System.out.println(INSERT_STUDENT_SQL);
        //câu lệnh try-with-resource sẽ tự động đóng kết nối.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getDateOfBirth());
            preparedStatement.setString(3,student.getAddress());
            preparedStatement.setString(4,student.getPhoneNumber());
            preparedStatement.setString(5,student.getEmail());
            preparedStatement.setString(6,student.getClassroom());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Tao moi sinh vien that bai");
        }
    }
    public Student selectStudent(int id) {
        Student student = null;
        // Bước 1: Thiết lập kết nối
        try (Connection connection = getConnection();
             //Bước 2:Tạo một câu lệnh bằng cách sử dụng đối tượng kết nối
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Bước 3: Thực thi truy vấn hoặc cập nhật truy vấn
            ResultSet rs = preparedStatement.executeQuery();

            // Bước 4: Xử lý đối tượng ResultSet
            while (rs.next()) {
                String name = rs.getString("name");
                String dateOfBirth = rs.getString("dateOfBirth");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String classroom = rs.getString("classroom");
                student = new Student(name,dateOfBirth,address,phoneNumber,email,classroom);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }
    public List<Student> selectAllStudent() {

        // sử dụng try-with-resources để tránh đóng tài nguyên
        List<Student> students = new ArrayList<>();
        // Step 1: Thiết lập kết nối
        try (Connection connection = getConnection();

             // Step 2:Tạo một câu lệnh bằng cách sử dụng đối tượng kết nối
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT);) {
            System.out.println(preparedStatement);
            // Step 3: Thực thi truy vấn hoặc cập nhật truy vấn
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Xử lý đối tượng ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dateOfBirth = rs.getString("dateOfBirth");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String classroom = rs.getString("classroom");
                students.add(new Student( id,name,dateOfBirth,address,phoneNumber,email,classroom));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return students;
    }
    public boolean deleteStudent(int id){
        boolean rowDeleted = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Xoa that bai");
        }
        return rowDeleted;
    }

    public boolean updateStudent(Student student){
        boolean rowUpdated = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getDateOfBirth());
            statement.setString(3, student.getAddress());
            statement.setString(4,student.getPhoneNumber());
            statement.setString(5,student.getEmail());
            statement.setString(6,student.getClassroom());
            statement.setInt(7,student.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Cap nhat that bai");
        }
        return rowUpdated;
    }
    @Override
    public List<Student> searchStudentByName(String input) {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(SEARCH_STUDENT_BY_NAME);
            pstm.setString(1, "%"+input+"%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dateOfBirth = rs.getString("dateOfBirth");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String classroom = rs.getString("classroom");
                students.add(new Student( id,name,dateOfBirth,address,phoneNumber,email,classroom));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    @Override
    public List<Student> sortStudentByName() {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(SORT_STUDENT_BY_NAME);
            ResultSet rs = prepareStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dateOfBirth = rs.getString("dateOfBirth");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String classroom = rs.getString("classroom");
                students.add(new Student( id,name,dateOfBirth,address,phoneNumber,email,classroom));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
}
