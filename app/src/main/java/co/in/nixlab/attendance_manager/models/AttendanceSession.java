package co.in.nixlab.attendance_manager.models;

public class AttendanceSession {

    private int attendance_session_id;
    private int attendance_session_faculty_id;
    private String attendance_session_branch;
    private String attendance_session_sem;
    private String attendance_session_date;
    private String attendance_session_subject;

    public int getAttendance_session_id() {
        return attendance_session_id;
    }

    public void setAttendance_session_id(int attendance_session_id) {
        this.attendance_session_id = attendance_session_id;
    }

    public int getAttendance_session_faculty_id() {
        return attendance_session_faculty_id;
    }

    public void setAttendance_session_faculty_id(int attendance_session_faculty_id) {
        this.attendance_session_faculty_id = attendance_session_faculty_id;
    }

    public String getAttendance_session_branch() {
        return attendance_session_branch;
    }

    public void setAttendance_session_branch(
            String attendance_session_branch) {
        this.attendance_session_branch = attendance_session_branch;
    }

    public String getAttendance_session_sem() {
        return attendance_session_sem;
    }

    public void setAttendance_session_sem(String attendance_session_sem) {
        this.attendance_session_sem = attendance_session_sem;
    }

    public String getAttendance_session_date() {
        return attendance_session_date;
    }

    public void setAttendance_session_date(String attendance_session_date) {
        this.attendance_session_date = attendance_session_date;
    }

    public String getAttendance_session_subject() {
        return attendance_session_subject;
    }

    public void setAttendance_session_subject(String attendance_session_subject) {
        this.attendance_session_subject = attendance_session_subject;
    }


}
