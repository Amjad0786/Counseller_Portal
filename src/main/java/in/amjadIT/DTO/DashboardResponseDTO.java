package in.amjadIT.DTO;

public class DashboardResponseDTO {
    private int totalEnqCnt;
    private int openEnqCnt;
    private int enrolledEnqCnt;
    private int lostEnqCnt;

    // Getters and Setters
    public int getTotalEnqCnt() {
        return totalEnqCnt;
    }

    public void setTotalEnqCnt(int totalEnqCnt) {
        this.totalEnqCnt = totalEnqCnt;
    }

    public int getOpenEnqCnt() {
        return openEnqCnt;
    }

    public void setOpenEnqCnt(int openEnqCnt) {
        this.openEnqCnt = openEnqCnt;
    }

    public int getEnrolledEnqCnt() {
        return enrolledEnqCnt;
    }

    public void setEnrolledEnqCnt(int enrolledEnqCnt) {
        this.enrolledEnqCnt = enrolledEnqCnt;
    }

    public int getLostEnqCnt() {
        return lostEnqCnt;
    }

    public void setLostEnqCnt(int lostEnqCnt) {
        this.lostEnqCnt = lostEnqCnt;
    }
}
