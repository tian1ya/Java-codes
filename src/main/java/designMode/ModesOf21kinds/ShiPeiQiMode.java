package designMode.ModesOf21kinds;

import java.util.HashMap;
import java.util.Map;

interface IUserInfo {
    String getUserName();

    String getHomeAddress();

    String getMobileNumber();

    String getOfficeTelNumber();

    String getJobPosition();

    String getHomeTelNumber();

}

class UserInfo implements IUserInfo {

    @Override
    public String getUserName() {
        System.out.println("This is user name of employee");
        return null;
    }

    @Override
    public String getHomeAddress() {
        System.out.println("This is user home address of employee");

        return null;
    }

    @Override
    public String getMobileNumber() {
        System.out.println("This is user mobile number of employee");
        return null;

    }

    @Override
    public String getOfficeTelNumber() {
        System.out.println("This is user office number of employee");
        return null;
    }

    @Override
    public String getJobPosition() {
        System.out.println("This is user job position of employee");

        return null;
    }

    @Override
    public String getHomeTelNumber() {
        System.out.println("This is user home number of employee");
        return null;
    }
}

interface IOuterUser {
    Map getUserBaseInfo();
    Map getUserOfficeInfo();
    Map getUserHomeInfo();
}


// 三个外界类接口
interface IOuterUserBaseInfo {
    Map getUserBaseInfo();
}

// 三个外界类接口
interface IOuterUserHomeInfo {
    Map getUserHomeInfo();
}

// 三个外界类接口
interface IOuterUserOfficeInfo {
    Map getUserOfficeInfo();
}

// 三个外界类
class OuterUserBaseInfo implements IOuterUserBaseInfo {

    @Override
    public Map getUserBaseInfo() {
        HashMap<String,String> baseInfoMap = new HashMap<>();
        baseInfoMap.put("userName","这个员工叫混世魔王");
        baseInfoMap.put("mobileNumber","这个员工的电话是");
        return baseInfoMap;
    }
}

// 三个外界类
class OuterUserHomeInfo implements IOuterUserHomeInfo {

    @Override
    public Map getUserHomeInfo() {
        HashMap<String,String> baseInfoMap = new HashMap<>();
        baseInfoMap.put("homeTelNumber","员工的家庭电话是。");
        baseInfoMap.put("homeAddress","员工的家庭住址是");
        return baseInfoMap;
    }
}

// 三个外界类
class OuterUserOfficeInfo implements IOuterUserOfficeInfo {

    public Map getUserOfficeInfo() {
        HashMap<String,String> baseInfoMap = new HashMap<>();
        baseInfoMap.put("jobPosotion","这个人的职位是BOSS");
        baseInfoMap.put("officeTelNumber","员工的办公室电话");
        return baseInfoMap;
    }
}

 // OuterUserInfo 变成了委托类，将 IUserInfo
 // 需要的接口实现都委托给其它三个实现类，
class OuterUserInfo implements IUserInfo {

    // 源目标对象，
    private IOuterUserBaseInfo baseInfo = null;
    private IOuterUserHomeInfo homeInfo = null;
    private IOuterUserOfficeInfo officeInfo = null;

    // 数据处理
    private Map<String, String> baseMap = null;
    private Map<String, String> homeMap = null;
    private Map<String, String> officeMap = null;

    // 构造函数传递对象，通过构造函数 将多个外部类 聚合在一起
    public OuterUserInfo(IOuterUserBaseInfo baseInfo,
                         IOuterUserHomeInfo homeInfo,
                         IOuterUserOfficeInfo officeInfo) {

        this.baseInfo = baseInfo;
        this.homeInfo = homeInfo;
        this.officeInfo = officeInfo;
        this.baseMap = baseInfo.getUserBaseInfo();
        this.homeMap = homeInfo.getUserHomeInfo();
        this.officeMap = officeInfo.getUserOfficeInfo();
    }

    // 家庭地址
    public String getHomeAddress() {
        String homeAddress = this.homeMap.get("homeAddress");
        System.out.println(homeAddress);
        return homeAddress;
    }
    // 家庭电话号码
    public String getHomeTelNumber() {
        String homeTelNumber = this.homeMap.get("homeTelNumber");
        System.out.println(homeTelNumber);
        return homeTelNumber;
    }
    // 职位信息
    public String getJobPosition() {
        String jobPosition = this.officeMap.get("jobPosition");
        System.out.println(jobPosition);
        return jobPosition;
    }
    // 收机号码
    public String getMobileNumber() {
        String mobileNumber = this.baseMap.get("mobileNumber");
        System.out.println(mobileNumber);
        return mobileNumber;
    }
    // 办公电话
    public String getOfficeTelNumber() {
        String OfficeTelNumber = this.officeMap.get("OfficeTelNumber");
        System.out.println(OfficeTelNumber);
        return OfficeTelNumber;
    }
    // 员工名称
    public String getUserName() {
        String userName = this.baseMap.get("userName");
        System.out.println(userName);
        return userName;
    }
}

public class ShiPeiQiMode {
    public static void main(String[] args) {
        OuterUserBaseInfo outerUserBaseInfo = new OuterUserBaseInfo();
        OuterUserHomeInfo outerUserHomeInfo = new OuterUserHomeInfo();
        OuterUserOfficeInfo outerUserOfficeInfo = new OuterUserOfficeInfo();

        OuterUserInfo outerUserInfo = new OuterUserInfo(
                outerUserBaseInfo,
                outerUserHomeInfo,
                outerUserOfficeInfo);

        for (int i = 0; i < 5; i++) {
           outerUserInfo.getMobileNumber();
        }
    }
}






















