package gr.aueb.cf.springschoolapp.dto.meetingdto;

public class MeetingInsertDTO {
    private Long studentId;
    private Long teacherId;
    private String meetingRoom;
    private String meetingDate;

    public MeetingInsertDTO() {
    }

    public MeetingInsertDTO(Long studentId, Long teacherId, String meetingRoom, String meetingDate) {
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.meetingRoom = meetingRoom;
        this.meetingDate = meetingDate;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    @Override
    public String toString() {
        return "MeetingInsertDTO{" +
                "studentId=" + studentId +
                ", teacherId=" + teacherId +
                ", meetingRoom='" + meetingRoom + '\'' +
                ", meetingDate='" + meetingDate + '\'' +
                '}';
    }
}
