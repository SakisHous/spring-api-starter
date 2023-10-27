package gr.aueb.cf.springschoolapp.dto.meetingdto;

public class MeetingUpdateDTO {
    private Long id;
    private Long studentId;
    private Long teacherId;
    private String meetingRoom;
    private String meetingDate;

    public MeetingUpdateDTO() {
    }

    public MeetingUpdateDTO(Long id, Long studentId, Long teacherId, String meetingRoom, String meetingDate) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.meetingRoom = meetingRoom;
        this.meetingDate = meetingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "MeetingUpdateDTO{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", teacherId=" + teacherId +
                ", meetingRoom='" + meetingRoom + '\'' +
                ", meetingDate='" + meetingDate + '\'' +
                '}';
    }
}
