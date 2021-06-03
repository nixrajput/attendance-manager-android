package co.in.nixlab.attendance_manager.controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import co.in.nixlab.attendance_manager.models.Attendance;
import co.in.nixlab.attendance_manager.models.AttendanceSession;
import co.in.nixlab.attendance_manager.models.Faculty;
import co.in.nixlab.attendance_manager.models.Student;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Attendance";

    private static final String FACULTY_INFO_TABLE = "faculty_table";
    private static final String STUDENT_INFO_TABLE = "student_table";
    private static final String ATTENDANCE_SESSION_TABLE = "attendance_session_table";
    private static final String ATTENDANCE_TABLE = "attendance_table";


    private static final String KEY_FACULTY_ID = "faculty_id";
    private static final String KEY_FACULTY_FIRSTNAME = "faculty_firstname";
    private static final String KEY_FACULTY_LASTNAME = "faculty_Lastname";
    private static final String KEY_FACULTY_MO_NO = "faculty_mobilenumber";
    private static final String KEY_FACULTY_ADDRESS = "faculty_address";
    private static final String KEY_FACULTY_USERNAME = "faculty_username";
    private static final String KEY_FACULTY_PASSWORD = "faculty_password";

    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_STUDENT_FIRSTNAME = "student_firstname";
    private static final String KEY_STUDENT_LASTNAME = "student_lastname";
    private static final String KEY_STUDENT_MO_NO = "student_mobilenumber";
    private static final String KEY_STUDENT_ADDRESS = "student_address";
    private static final String KEY_STUDENT_BRANCH = "student_branch";
    private static final String KEY_STUDENT_SEM = "student_sem";

    private static final String KEY_ATTENDANCE_SESSION_ID = "attendance_session_id";
    private static final String KEY_ATTENDANCE_SESSION_FACULTY_ID = "attendance_session_faculty_id";
    private static final String KEY_ATTENDANCE_SESSION_BRANCH = "attendance_session_branch";
    private static final String KEY_ATTENDANCE_SESSION_SEM = "attendance_session_sem";
    private static final String KEY_ATTENDANCE_SESSION_DATE = "attendance_session_date";
    private static final String KEY_ATTENDANCE_SESSION_SUBJECT = "attendance_session_subject";

    private static final String KEY_SESSION_ID = "attendance_session_id";
    private static final String KEY_ATTENDANCE_STUDENT_ID = "attendance_student_id";
    private static final String KEY_ATTENDANCE_STATUS = "attendance_status";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryFaculty = "CREATE TABLE " + FACULTY_INFO_TABLE + " (" +
                KEY_FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FACULTY_FIRSTNAME + " TEXT, " +
                KEY_FACULTY_LASTNAME + " TEXT, " +
                KEY_FACULTY_MO_NO + " TEXT, " +
                KEY_FACULTY_ADDRESS + " TEXT," +
                KEY_FACULTY_USERNAME + " TEXT," +
                KEY_FACULTY_PASSWORD + " TEXT " + ")";
        Log.d("queryFaculty", queryFaculty);


        String queryStudent = "CREATE TABLE " + STUDENT_INFO_TABLE + " (" +
                KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STUDENT_FIRSTNAME + " TEXT, " +
                KEY_STUDENT_LASTNAME + " TEXT, " +
                KEY_STUDENT_MO_NO + " TEXT, " +
                KEY_STUDENT_ADDRESS + " TEXT," +
                KEY_STUDENT_BRANCH + " TEXT," +
                KEY_STUDENT_SEM + " TEXT " + ")";
        Log.d("queryStudent", queryStudent);


        String queryAttendanceSession = "CREATE TABLE " + ATTENDANCE_SESSION_TABLE + " (" +
                KEY_ATTENDANCE_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_ATTENDANCE_SESSION_FACULTY_ID + " INTEGER, " +
                KEY_ATTENDANCE_SESSION_BRANCH + " TEXT, " +
                KEY_ATTENDANCE_SESSION_SEM + " TEXT, " +
                KEY_ATTENDANCE_SESSION_DATE + " DATE," +
                KEY_ATTENDANCE_SESSION_SUBJECT + " TEXT" + ")";

        String queryAttendance = "CREATE TABLE " + ATTENDANCE_TABLE + " (" +
                KEY_SESSION_ID + " INTEGER, " +
                KEY_ATTENDANCE_STUDENT_ID + " INTEGER, " +
                KEY_ATTENDANCE_STATUS + " TEXT " + ")";

        try {
            db.execSQL(queryFaculty);
            db.execSQL(queryStudent);
            db.execSQL(queryAttendanceSession);
            db.execSQL(queryAttendance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        String queryFaculty = "CREATE TABLE " + FACULTY_INFO_TABLE + " (" +
                KEY_FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FACULTY_FIRSTNAME + " TEXT, " +
                KEY_FACULTY_LASTNAME + " TEXT, " +
                KEY_FACULTY_MO_NO + " TEXT, " +
                KEY_FACULTY_ADDRESS + " TEXT," +
                KEY_FACULTY_USERNAME + " TEXT," +
                KEY_FACULTY_PASSWORD + " TEXT " + ")";


        String queryStudent = "CREATE TABLE " + STUDENT_INFO_TABLE + " (" +
                KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STUDENT_FIRSTNAME + " TEXT, " +
                KEY_STUDENT_LASTNAME + " TEXT, " +
                KEY_STUDENT_MO_NO + " TEXT, " +
                KEY_STUDENT_ADDRESS + " TEXT," +
                KEY_STUDENT_BRANCH + " TEXT," +
                KEY_STUDENT_SEM + " TEXT " + ")";


        String queryAttendanceSession = "CREATE TABLE " + ATTENDANCE_SESSION_TABLE + " (" +
                KEY_ATTENDANCE_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_ATTENDANCE_SESSION_FACULTY_ID + " INTEGER, " +
                KEY_ATTENDANCE_SESSION_BRANCH + " TEXT, " +
                KEY_ATTENDANCE_SESSION_SEM + " TEXT, " +
                KEY_ATTENDANCE_SESSION_DATE + " TEXT," +
                KEY_ATTENDANCE_SESSION_SUBJECT + " TEXT" + ")";


        String queryAttendance = "CREATE TABLE " + ATTENDANCE_TABLE + " (" +
                KEY_SESSION_ID + " INTEGER, " +
                KEY_ATTENDANCE_STUDENT_ID + " INTEGER, " +
                KEY_ATTENDANCE_STATUS + " TEXT " + ")";

        try {
            db.execSQL(queryFaculty);
            db.execSQL(queryStudent);
            db.execSQL(queryAttendanceSession);
            db.execSQL(queryAttendance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFaculty(Faculty faculty) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO faculty_table (faculty_firstname, faculty_Lastname," +
                "faculty_mobilenumber, faculty_address, faculty_username, faculty_password) " +
                "values ('" +
                faculty.getFaculty_firstname() + "', '" +
                faculty.getFaculty_lastname() + "', '" +
                faculty.getFaculty_mobile_number() + "', '" +
                faculty.getFaculty_address() + "', '" +
                faculty.getFaculty_username() + "', '" +
                faculty.getFaculty_password() + "')";
        db.execSQL(query);
        db.close();
    }

    public Faculty validateFaculty(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM faculty_table where faculty_username='" + userName +
                "' and faculty_password='" + password + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            Faculty faculty = new Faculty();
            faculty.setFaculty_id(Integer.parseInt(cursor.getString(0)));
            faculty.setFaculty_firstname(cursor.getString(1));
            faculty.setFaculty_lastname(cursor.getString(2));
            faculty.setFaculty_mobile_number(cursor.getString(3));
            faculty.setFaculty_address(cursor.getString(4));
            faculty.setFaculty_username(cursor.getString(5));
            faculty.setFaculty_password(cursor.getString(6));
            return faculty;
        }
        cursor.close();
        db.close();
        return null;
    }

    public ArrayList<Faculty> getAllFaculty() {
        ArrayList<Faculty> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM faculty_table";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Faculty faculty = new Faculty();
                faculty.setFaculty_id(Integer.parseInt(cursor.getString(0)));
                faculty.setFaculty_firstname(cursor.getString(1));
                faculty.setFaculty_lastname(cursor.getString(2));
                faculty.setFaculty_mobile_number(cursor.getString(3));
                faculty.setFaculty_address(cursor.getString(4));
                faculty.setFaculty_username(cursor.getString(5));
                faculty.setFaculty_password(cursor.getString(6));
                list.add(faculty);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public void deleteFaculty(int facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM faculty_table WHERE faculty_id=" + facultyId;

        db.execSQL(query);
        db.close();
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO student_table (student_firstname, student_lastname," +
                "student_mobilenumber, student_address, student_branch, student_sem) " +
                "values ('" +
                student.getStudent_firstname() + "', '" +
                student.getStudent_lastname() + "','" +
                student.getStudent_mobile_number() + "', '" +
                student.getStudent_address() + "', '" +
                student.getStudent_branch() + "', '" +
                student.getStudent_sem() + "')";
        db.execSQL(query);
        db.close();
    }

    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setStudent_firstname(cursor.getString(1));
                student.setStudent_lastname(cursor.getString(2));
                student.setStudent_mobile_number(cursor.getString(3));
                student.setStudent_address(cursor.getString(4));
                student.setStudent_branch(cursor.getString(5));
                student.setStudent_sem(cursor.getString(6));
                list.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Student> getAllStudentByBranchSem(String branch, String semester) {
        ArrayList<Student> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table where student_branch='" + branch +
                "' and student_sem='" + semester + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setStudent_firstname(cursor.getString(1));
                student.setStudent_lastname(cursor.getString(2));
                student.setStudent_mobile_number(cursor.getString(3));
                student.setStudent_address(cursor.getString(4));
                student.setStudent_branch(cursor.getString(5));
                student.setStudent_sem(cursor.getString(6));
                list.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public Student getStudentById(int studentId) {
        Student student = new Student();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM student_table where student_id=" + studentId;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setStudent_firstname(cursor.getString(1));
                student.setStudent_lastname(cursor.getString(2));
                student.setStudent_mobile_number(cursor.getString(3));
                student.setStudent_address(cursor.getString(4));
                student.setStudent_branch(cursor.getString(5));
                student.setStudent_sem(cursor.getString(6));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return student;
    }

    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM student_table WHERE student_id=" + studentId;

        db.execSQL(query);
        db.close();
    }

    public int addAttendanceSession(AttendanceSession attendanceSession) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO attendance_session_table (attendance_session_faculty_id, " +
                "attendance_session_branch, attendance_session_sem, attendance_session_date," +
                "attendance_session_subject) values ('" +
                attendanceSession.getAttendance_session_faculty_id() + "', '" +
                attendanceSession.getAttendance_session_branch() + "','" +
                attendanceSession.getAttendance_session_sem() + "', '" +
                attendanceSession.getAttendance_session_date() + "', '" +
                attendanceSession.getAttendance_session_subject() + "')";
        db.execSQL(query);

        String get_max_sessionId = "SELECT max(attendance_session_id) FROM attendance_session_table";
        Cursor cursor = db.rawQuery(get_max_sessionId, null);

        if (cursor.moveToFirst()) {
            return Integer.parseInt(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return 0;
    }

    public ArrayList<AttendanceSession> getAllAttendanceSession() {
        ArrayList<AttendanceSession> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM attendance_session_table";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                AttendanceSession attendanceSession = new AttendanceSession();
                attendanceSession.setAttendance_session_id(Integer.parseInt(cursor.getString(0)));
                attendanceSession.setAttendance_session_faculty_id(Integer.parseInt(cursor.getString(1)));
                attendanceSession.setAttendance_session_branch(cursor.getString(2));
                attendanceSession.setAttendance_session_sem(cursor.getString(3));
                attendanceSession.setAttendance_session_date(cursor.getString(4));
                attendanceSession.setAttendance_session_subject(cursor.getString(5));
                list.add(attendanceSession);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public void deleteAttendanceSession(int attendanceSessionId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM attendance_session_table WHERE attendance_session_id=" +
                attendanceSessionId;

        db.execSQL(query);
        db.close();
    }

    public void addNewAttendance(Attendance attendance) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO attendance_table values (" +
                attendance.getAttendance_session_id() + ", " +
                attendance.getAttendance_student_id() + ", '" +
                attendance.getAttendance_status() + "')";
        db.execSQL(query);
        db.close();
    }


    public ArrayList<Attendance> getAttendanceBySessionID(AttendanceSession attendanceSession) {
        int attendanceSessionId = 0;
        ArrayList<Attendance> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM attendance_session_table where attendance_session_faculty_id="
                + attendanceSession.getAttendance_session_faculty_id() + ""
                + " AND attendance_session_branch='"
                + attendanceSession.getAttendance_session_branch()
                + "' AND attendance_session_sem='"
                + attendanceSession.getAttendance_session_sem() + "'" +
                " AND attendance_session_date='"
                + attendanceSession.getAttendance_session_date()
                + "' AND attendance_session_subject='"
                + attendanceSession.getAttendance_session_subject() + "'";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                attendanceSessionId = (Integer.parseInt(cursor.getString(0)));
            } while (cursor.moveToNext());
        }

        String query1 = "SELECT * FROM attendance_table where attendance_session_id=" +
                attendanceSessionId + " order by attendance_student_id";

        Cursor cursor1 = db.rawQuery(query1, null);
        if (cursor1.moveToFirst()) {
            do {
                Attendance attendanceBean = new Attendance();
                attendanceBean.setAttendance_session_id(Integer.parseInt(cursor1.getString(0)));
                attendanceBean.setAttendance_student_id(Integer.parseInt(cursor1.getString(1)));
                attendanceBean.setAttendance_status(cursor1.getString(2));
                list.add(attendanceBean);
            } while (cursor1.moveToNext());
        }

        cursor1.close();
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Attendance> getTotalAttendanceBySessionID(AttendanceSession attendanceSession) {
        int attendanceSessionId;
        ArrayList<Attendance> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM attendance_session_table WHERE attendance_session_faculty_id="
                + attendanceSession.getAttendance_session_faculty_id() + ""
                + " AND attendance_session_branch='"
                + attendanceSession.getAttendance_session_branch()
                + "' AND attendance_session_sem='"
                + attendanceSession.getAttendance_session_sem() + "'" +
                " AND attendance_session_subject='"
                + attendanceSession.getAttendance_session_subject() + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                attendanceSessionId = (Integer.parseInt(cursor.getString(0)));

                String query1 = "SELECT * FROM attendance_table WHERE attendance_session_id=" +
                        attendanceSessionId + " order by attendance_student_id";
                Cursor cursor1 = db.rawQuery(query1, null);
                if (cursor1.moveToFirst()) {
                    do {
                        Attendance attendance = new Attendance();
                        attendance.setAttendance_session_id(Integer.parseInt(cursor1.getString(0)));
                        attendance.setAttendance_student_id(Integer.parseInt(cursor1.getString(1)));
                        attendance.setAttendance_status(cursor1.getString(2));
                        list.add(attendance);

                    } while (cursor1.moveToNext());
                }

                Attendance attendance = new Attendance();
                attendance.setAttendance_session_id(0);
                attendance.setAttendance_status("Date : " + cursor.getString(4));
                list.add(attendance);
                cursor1.close();
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Attendance> getAllAttendanceByStudent() {
        ArrayList<Attendance> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT attendance_student_id,count(*) FROM attendance_table where " +
                "attendance_status='P' group by attendance_student_id";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Attendance attendance = new Attendance();
                attendance.setAttendance_student_id(Integer.parseInt(cursor.getString(0)));
                attendance.setAttendance_session_id(Integer.parseInt(cursor.getString(1)));
                list.add(attendance);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
	/*public ArrayList<AttendanceBean> getAllAttendanceBySessionID(int sessionId)
	{
		ArrayList<AttendanceBean> list = new ArrayList<AttendanceBean>();

		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM attendance_table where attendance_session_id=" + sessionId;
		Cursor cursor = db.rawQuery(query, null);

		if(!cursor.moveToFirst()) 
		{
			do{
				AttendanceBean attendanceBean = new AttendanceBean();
				attendanceBean.setAttendance_session_id(Integer.parseInt(cursor.getString(0)));
				attendanceBean.setAttendance_student_id(Integer.parseInt(cursor.getString(1)));
				attendanceBean.setAttendance_status(cursor.getString(2));
				list.add(attendanceBean);

			}while(cursor.moveToNext());
		}
		return list;
	}*/


    // Creating Tables
	/*@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_User_Info_TABLE = "CREATE TABLE " + TABLE_INFO_USER + "("
				+ KEY_ID + " INTEGER PRIMARY KEY, " + KEY_FIRSTNAME + " TEXT, "+ KEY_LASTNAME + " TEXT, " +KEY_MO_NO +" TEXT, "
				+KEY_EMAIL +" TEXT, " +KEY_USERNAME +" TEXT, " + KEY_PASSWORD +" TEXT " + ")";

		Log.d("rupali",CREATE_User_Info_TABLE );
		db.execSQL(CREATE_User_Info_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO_USER);

		// Create tables again
		onCreate(db);
	}

	void addUserInfo(UserInfo userinfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FIRSTNAME, userinfo.getUser_Firstname()); //  Name
		values.put(KEY_LASTNAME, userinfo.getUser_Lastname()); //  Name
		values.put(KEY_MO_NO, userinfo.getUser_MobileNo()); // Contact Phone
		values.put(KEY_EMAIL, userinfo.getUser_EmailId());
		values.put(KEY_USERNAME, userinfo.getUser_Username());
		values.put(KEY_PASSWORD, userinfo.getUser_Password());

		// Inserting Row
		db.insert(TABLE_INFO_USER, null, values);
		//2nd argument is String containing nullColumnHack
		db.close(); // Closing database connection
	}


	// Getting single contact
	UserInfo getUserInfo(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_INFO_USER, new String[] { KEY_ID,
				KEY_FIRSTNAME, KEY_LASTNAME,KEY_MO_NO,  KEY_EMAIL, KEY_USERNAME, KEY_PASSWORD }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		UserInfo userinfo = new UserInfo(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6));
		// return contact
				return userinfo;
	}

	public UserInfo validateUser(String username, String password)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "Select * from User_Info_Table WHERE User_Username='"+ username +"' AND User_Password='"+password+"'";
		Log.d("Rupali", "Login QUERY:" + query);

		Cursor cursor = db.rawQuery(query, null);


		if(!cursor.moveToFirst()) 
		{
			Log.d("Rupali", "cursor is null.. returing NULL");
			return null;
		}
		Log.d("Rupali", "cursor is NOT null.. we got user data...");


		UserInfo userinfo = new UserInfo(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6));

		return userinfo;
	}

	// Updating single contact
	public int updateUserPassword(UserInfo userinfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PASSWORD, userinfo.getUser_Password());


		// updating row
		return db.update(TABLE_INFO_USER, values, KEY_ID + " = ?",
				new String[] { String.valueOf(userinfo.getUser_id()) });
	}

	public int updateUserContact(UserInfo userinfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_MO_NO, userinfo.getUser_MobileNo());
		values.put(KEY_EMAIL, userinfo.getUser_EmailId());


		// updating row
		return db.update(TABLE_INFO_USER, values, KEY_ID + " = ?",
				new String[] { String.valueOf(userinfo.getUser_id()) });
	}


	//veiw details

	public UserInfo viewUserInfo(String id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String query = "Select * from User_Info_Table WHERE id='"+id+"'";
		Cursor cursor = db.rawQuery(query, null);
		if(!cursor.moveToFirst()) 
		{
			Log.d("Rupali", "cursor is null.. returing NULL");
			return null;
		}
		Log.d("Rupali", "cursor is NOT null.. we got user data...");

		UserInfo userinfo = new UserInfo(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6));
		// return contact
		return userinfo;
	}



	 // Getting All users
    public List<UserInfo> getAllUserInfo() {
        List<UserInfo> userinfolist = new ArrayList<UserInfo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_INFO_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                UserInfo userinfo=new UserInfo();

                userinfo.setUser_id(Integer.parseInt(cursor.getString(0)));
                userinfo.setUser_Lastname(cursor.getString(2));
                userinfo.setUser_Username(cursor.getString(5));
                userinfo.setUser_Firstname(cursor.getString(1));



                // Adding contact to list
                userinfolist.add(userinfo);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userinfolist;
    }

    // Deleting single contact
    public void deleteUser(UserInfo userinfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INFO_USER, KEY_ID + " = ?",
                new String[] { String.valueOf(userinfo.getUser_id()) });
        db.close();
    }
	  */
}