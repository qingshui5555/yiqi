package com.health.service.security;

import com.health.bo.security.UserQueryBo;
import com.health.common.Constant;
import com.health.dao.clinic.ClinicDao;
import com.health.dao.department.DepartmentDao;
import com.health.dao.role.RoleDao;
import com.health.dao.security.*;
import com.health.dto.security.LoginDto;
import com.health.dto.security.UserDto;
import com.health.entity.department.DepartmentEntity;
import com.health.entity.role.RoleEntity;
import com.health.entity.security.*;
import com.health.exception.RequestConflictedException;
import com.health.service.BaseService;
import com.health.service.sms.SmsService;
import com.health.transfer.security.LoginTransfer;
import com.health.transfer.security.UserTransfer;
import com.health.util.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.HttpHeaders;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author henry
 */
public class UserService extends BaseService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserProfileDao userProfileDao;
    
    @Autowired
    private UserRoleDao userRoleDao;
    
    @Autowired
    private UserLoginDao userLoginDao;
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private RandomDao randomDao;
    
    @Autowired
    private ClinicDao clinicDao;
    
    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private SmsService smsService;
    
    /**
     * 条件查询角色列表
     * @param httpHeaders
     * @param queryBo
     * @return
     */
    public List<UserTransfer> query(final HttpHeaders httpHeaders, final UserQueryBo queryBo) {
        List<UserTransfer> transferList = userDao.queryListUserTransfer(queryBo);
        for(UserTransfer transfer : transferList){
            RoleEntity role = new RoleEntity();
            role.setRoleId(transfer.getRoleId());
            role.setRoleName(transfer.getRoleName());
            List<RoleEntity> roleList = new ArrayList<>();
            roleList.add(role);
            transfer.setRoleList(roleList);
        }
        return transferList;
    }
    
    /**
     * 查询有排班的医生
     * @param httpHeaders
     * @param queryBo
     * @return
     */
    public List<UserTransfer> querySchedule(final HttpHeaders httpHeaders, final UserQueryBo queryBo) {
        List<UserTransfer> transferList = userDao.queryListScheduleUserTransfer(queryBo);
        for(UserTransfer transfer : transferList){
            RoleEntity role = new RoleEntity();
            role.setRoleId(transfer.getRoleId());
            role.setRoleName(transfer.getRoleName());
            List<RoleEntity> roleList = new ArrayList<>();
            roleList.add(role);
            transfer.setRoleList(roleList);
        }
        return transferList;
    }
    /**
     * 创建系统管理员或客服
     * @param httpHeaders
     * @param userDto
     * @return
     */
    public Long createWebUser(HttpHeaders httpHeaders, UserDto userDto){
        Long createBy = 1L;
        Date createOn = new Date();
        String mobile = userDto.getMobile();
        if(StringUtils.isBlank(mobile)){
            throw new RequestConflictedException("E0000001", "mobile");
        }
        String account = userDto.getAccount();
        if(StringUtils.isBlank(account)){
            throw new RequestConflictedException("E0000001", "account");
        }
        
        String password = userDto.getPassword();
        if(StringUtils.isBlank(password)){
            throw new RequestConflictedException("E0000001", "passWord");
        }
        UserEntity userEntity = userDao.queryUserByAccount(null, account);
        if(userEntity != null){
            throw new RequestConflictedException("E0000005", "account");
        }
        userEntity = userDao.queryUserByMobile(null, mobile);
        if(userEntity != null){
            throw new RequestConflictedException("E0000005", "mobile");
        }
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDto, user);
        user.setType(Constant.TYPE_USER_WEB);
        String slat = TokenUtils.makeSlat();
        user.setSlat(slat);
        user.setPassword(TokenUtils.makePassword(password, slat));
        user.setStatus(Constant.VALID);
        user.setVersion(Constant.DEFAULT_VERSION);
        user.setCreateOn(createOn);
        user.setCreateBy(createBy);
        user.setRegisterStatus(Constant.REGISTER_STATUS_ACCESS);
        userDao.createUserEntity(user);
        
        Long userId = user.getUserId();
        UserProfileEntity userProfile = new UserProfileEntity();
        if(StringUtils.isBlank(userDto.getName())){
            throw new RequestConflictedException("E0000001", "name");
        }
        BeanUtils.copyProperties(userDto, userProfile);
        userProfile.setUserId(userId);
        userProfile.setStatus(Constant.VALID);
        userProfile.setVersion(Constant.DEFAULT_VERSION);
        userProfile.setCreateOn(createOn);
        userProfile.setCreateBy(createBy);
        userProfileDao.createUserProfileEntity(userProfile);
        
        dealWithUserRole(userDto.getRoleList(), userId, createBy, createOn);
        String [] params = new String[]{account, password};
        smsService.sendWithParam(mobile, Constant.TEMPLATE_ID_REGISTER_SUCCESS, params);
        return userId;
    }
    
    /**
     * 医生注册
     * @param httpHeaders
     * @param userDto
     * @return
     */
    public Long registerDoctor(HttpHeaders httpHeaders, UserDto userDto){
        Long modifyBy = 1L;
        Date modifyOn = new Date();
        String mobile = userDto.getMobile();
        if(StringUtils.isBlank(mobile)){
            throw new RequestConflictedException("E0000001", "mobile");
        }
        String vCode = userDto.getvCode();
        if(StringUtils.isBlank(mobile)){
            throw new RequestConflictedException("E0000001", "vCode");
        }
        
        String password = userDto.getPassword();
        if(StringUtils.isBlank(password)){
            throw new RequestConflictedException("E0000001", "passWord");
        }

        UserEntity user = userDao.queryUserByCode(null, mobile, vCode);
        if(user == null){
            throw new RequestConflictedException("E0000002", "vCode");
        }
        user.setType(Constant.TYPE_USER_DOC);
        user.setAccount(getRandomAccount());
        user.setPassword(TokenUtils.makePassword(password, user.getSlat()));
        user.setModifyOn(modifyOn);
        user.setModifyBy(modifyBy);
        user.setRegisterStatus(Constant.REGISTER_STATUS_UNKNOWN);
        userDao.updateUserEntity(user);
        
        UserProfileEntity userProfile = userProfileDao.queryUserProfileEntity(user.getUserId());
        if(userProfile == null){
            throw new RequestConflictedException("E0000002", "vCode");
        }
        String name = userDto.getName();
        if(StringUtils.isBlank(name)){
            throw new RequestConflictedException("E0000001", "name");
        }
        userProfile.setName(name);
        userProfile.setClinicId(userDto.getClinicId());
        userProfile.setDepartmentId(userDto.getDepartmentId());
        userProfile.setIdentifier(userDto.getIdentifier());
        userProfile.setProfessionFirst(userDto.getProfessionFirst());
        userProfile.setProfessionSecond(userDto.getProfessionSecond());
        userProfile.setSkills(userDto.getSkills());
        userProfile.setTelephone(userDto.getTelephone());
        userProfile.setPayOut(userDto.getPayOut());
        userProfile.setPayOnline(userDto.getPayOnline());
        userProfile.setPayTelephone(userDto.getPayTelephone());
        userProfile.setPrePayOnline(userDto.getPrePayOnline());
        userProfile.setPrePayTelephone(userDto.getPrePayTelephone());
        userProfile.setPrePayOut(userDto.getPrePayOut());
        userProfile.setModifyOn(modifyOn);
        userProfile.setModifyBy(modifyBy);
        userProfileDao.updateUserProfileEntity(userProfile);
        RoleEntity role = new RoleEntity();
        role.setRoleId(2L);
        List<RoleEntity> roleEntityList = new ArrayList<>();
        roleEntityList.add(role);
        
        dealWithUserRole(roleEntityList, user.getUserId(), modifyBy, modifyOn);
        
        String[] params = new String[]{mobile};
        smsService.sendWithParam(mobile, Constant.TEMPLATE_ID_DOCTOR_REGISTER, params);
        
        return user.getUserId();
    }
    
    /**
     * 患者注册
     * @param httpHeaders
     * @param userDto
     * @return
     */
    public Long registerAppUser(HttpHeaders httpHeaders, UserDto userDto){
        Long modifyBy = 1L;
        Date modifyOn = new Date();
        String mobile = userDto.getMobile();
        if(StringUtils.isBlank(mobile)){
            throw new RequestConflictedException("E0000001", "mobile");
        }
        String vCode = userDto.getvCode();
        if(StringUtils.isBlank(vCode)){
            throw new RequestConflictedException("E0000001", "vCode");
        }
        
        String password = userDto.getPassword();
        if(StringUtils.isBlank(password)){
            throw new RequestConflictedException("E0000001", "passWord");
        }

        UserEntity user = userDao.queryUserByCode(Constant.TYPE_USER_APP, mobile, vCode);
        if(user == null){
            throw new RequestConflictedException("E0000002", "vCode");
        }
        user.setType(Constant.TYPE_USER_APP);
        user.setAccount(mobile);
        user.setPassword(TokenUtils.makePassword(password, user.getSlat()));
        user.setModifyOn(modifyOn);
        user.setModifyBy(modifyBy);
        user.setRegisterStatus(Constant.REGISTER_STATUS_UNKNOWN);
        userDao.updateUserEntity(user);
        
        UserProfileEntity userProfile = userProfileDao.queryUserProfileEntity(user.getUserId());
        if(userProfile == null){
            throw new RequestConflictedException("E0000002", "vCode");
        }
        String name = userDto.getName();
        if(StringUtils.isBlank(name)){
            throw new RequestConflictedException("E0000001", "name");
        }
        userProfile.setName(name);
        userProfile.setSex(userDto.getSex());
        userProfile.setBirthday(userDto.getBirthday());
        userProfile.setBlood(userDto.getBlood());
        userProfile.setChronic(userDto.getChronic());
        userProfile.setAllergy(userDto.getAllergy());
        userProfile.setAddress(userDto.getAddress());
        userProfile.setModifyOn(modifyOn);
        userProfile.setModifyBy(modifyBy);
        userProfileDao.updateUserProfileEntity(userProfile);
        
        return user.getUserId();
    }
    
    /**
     * 创建系统管理员或客服
     * @param httpHeaders
     * @param userDto
     * @return
     */
    public int updateUser(HttpHeaders httpHeaders, UserDto userDto){
        int result = -1;
        Long modifyBy = 1L;
        Date modifyOn = new Date();
        int type = userDto.getType();
        Long userId = userDto.getUserId();
        if(userId == null){
            throw new RequestConflictedException("E0000001", "userId");
        }
        String account = userDto.getAccount();
        if(StringUtils.isNotBlank(account)) {
            UserEntity userEntity = userDao.queryUserByAccount(type, account);
            if (userEntity != null && !userEntity.getUserId().equals(userId)) {
                throw new RequestConflictedException("E0000005", "account");
            }
        }
        String mobile = userDto.getMobile();
        if(StringUtils.isNotBlank(mobile)) {
            UserEntity userEntity = userDao.queryUserByMobile(type, mobile);
            if (userEntity != null && !userEntity.getUserId().equals(userId)) {
                throw new RequestConflictedException("E0000005", "mobile");
            }
        }
        
        UserEntity user = userDao.queryUserEntity(userId);
        if(user == null){
            throw new RequestConflictedException("E0000005", "account");
        }
        BeanUtils.copyProperties(userDto, user);
        String password = userDto.getPassword();
        if(StringUtils.isNotBlank(password)){
            String slat = user.getSlat();
            if(StringUtils.isBlank(slat)) {
                slat = TokenUtils.makeSlat();
                user.setSlat(slat);
            }
            user.setPassword(TokenUtils.makePassword(password, user.getSlat()));
        }
        user.setModifyOn(modifyOn);
        user.setModifyBy(modifyBy);
        result += userDao.updateUserEntity(user);
        
        UserProfileEntity userProfile = userProfileDao.queryUserProfileEntity(userId);
        if(userProfile == null){
            throw new RequestConflictedException("E0000005", "account");
        }
        BeanUtils.copyProperties(userDto, userProfile);
        userProfile.setModifyOn(modifyOn);
        userProfile.setModifyBy(modifyBy);
        result += userProfileDao.updateUserProfileEntity(userProfile);
        
        result += userRoleDao.deleteByUserId(userId);
        result += dealWithUserRole(userDto.getRoleList(), userId, modifyBy, modifyOn);
        
        return result;
    }
    
    /**
     * 批量审核
     * @param httpHeaders
     * @param userDto
     * @return
     */
    public int audit(HttpHeaders httpHeaders, UserDto userDto){
        int result = -1;
        Long modifyBy = 1L;
        Date modifyOn = new Date();
        if(userDto == null || userDto.getUserIdList() == null || userDto.getUserIdList().size() == 0){
            throw new RequestConflictedException("E0000001", "userIdList");
        }
        for(Long userId : userDto.getUserIdList()){
            UserEntity userEntity = userDao.queryUserEntity(userId);
            if(userEntity == null || !userEntity.getType().equals(Constant.TYPE_USER_DOC) || !userEntity.getRegisterStatus().equals(Constant.REGISTER_STATUS_UNKNOWN)){
                continue;
            }
            userEntity.setRegisterStatus(Constant.REGISTER_STATUS_ACCESS);
            userEntity.setModifyBy(modifyBy);
            userEntity.setModifyOn(modifyOn);
            result += userDao.updateUserEntity(userEntity);
    
            String[] params = new String[]{userEntity.getMobile(), userEntity.getAccount()};
            smsService.sendWithParam(userEntity.getMobile(), Constant.TEMPLATE_ID_DOCTOR_REGISTER_ACCESS, params);
        }
        
        return result;
    }
    
    /**
     * 批量拒绝
     * @param httpHeaders
     * @param userDto
     * @return
     */
    public int reject(HttpHeaders httpHeaders, UserDto userDto){
        int result = -1;
        Long modifyBy = 1L;
        Date modifyOn = new Date();
        if(userDto == null || userDto.getUserIdList() == null || userDto.getUserIdList().size() == 0){
            throw new RequestConflictedException("E0000001", "userIdList");
        }
        for(Long userId : userDto.getUserIdList()){
            UserEntity userEntity = userDao.queryUserEntity(userId);
            if(userEntity == null || !userEntity.getType().equals(Constant.TYPE_USER_DOC) || userEntity.getRegisterStatus().equals(Constant.REGISTER_STATUS_REJECT)){
                continue;
            }
            userEntity.setRegisterStatus(Constant.REGISTER_STATUS_REJECT);
            userEntity.setModifyBy(modifyBy);
            userEntity.setModifyOn(modifyOn);
            result += userDao.updateUserEntity(userEntity);
            
            String[] params = new String[]{userEntity.getMobile()};
            smsService.sendWithParam(userEntity.getMobile(), Constant.TEMPLATE_ID_DOCTOR_REGISTER_REJECT, params);
        }
        return result;
    }
    
    /**
     * 获取验证码
     * @param httpHeaders
     * @param mobile
     * @return
     */
    public String createCode(HttpHeaders httpHeaders, Integer type, String mobile){
        if(StringUtils.isBlank(mobile)){
            throw new RequestConflictedException("E0000001", "mobile");
        }
        Date createOn = new Date();
        String code = createRandomCode(4);
        String[] params = new String[]{code, "5"};
        smsService.sendWithParam(mobile, Constant.TEMPLATE_ID_CODE, params);
    
        UserEntity userEntity = userDao.queryUserByMobile(type, mobile);
        if(userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setType(type);
            userEntity.setMobile(mobile);
            userEntity.setvCode(code);
            userEntity.setvExpire(new Date(createOn.getTime() + Constant.CODE_EXPIRE_TIME));
            userEntity.setStatus(Constant.VALID);
            userEntity.setVersion(Constant.DEFAULT_VERSION);
            userEntity.setCreateOn(createOn);
            userDao.createUserEntity(userEntity);
            
            UserProfileEntity userProfileEntity = new UserProfileEntity();
            userProfileEntity.setUserId(userEntity.getUserId());
            userProfileEntity.setCreateOn(createOn);
            userProfileEntity.setStatus(Constant.VALID);
            userProfileEntity.setVersion(Constant.DEFAULT_VERSION);
            userProfileDao.createUserProfileEntity(userProfileEntity);
        } else {
            userEntity.setvCode(code);
            userEntity.setvExpire(new Date(createOn.getTime() + Constant.CODE_EXPIRE_TIME));
            userEntity.setModifyOn(createOn);
            userDao.updateUserEntity(userEntity);
        }
        
       
        
        return code;
    }
    
    /**
     * 登陆
     * @param httpHeaders
     * @param loginDto
     * @return
     */
    public LoginTransfer userLogin(HttpHeaders httpHeaders, LoginDto loginDto) {
        LoginTransfer loginTransfer = new LoginTransfer();
        BeanUtils.copyProperties(loginDto, loginTransfer);
        Long createBy = 1L;
        Date createOn = new Date();
        Integer loginType = loginDto.getLoginType();
        if(loginType == null ){
            throw new RequestConflictedException("E0000001", "loginType");
        }
        
        if(loginType.equals(Constant.LOGIN_TYPE_CODE)){
            String mobile = loginDto.getAccount();
            if(StringUtils.isBlank(mobile)){
                throw new RequestConflictedException("E0000001", "mobile");
            }
        
            String vCode = loginDto.getvCode();
            if (StringUtils.isBlank(vCode)){
                throw new RequestConflictedException("E0000001", "vCode");
            }
        
            UserEntity user = userDao.queryUserByCode(loginDto.getType(), mobile, vCode);
            if(user == null){
                throw new RequestConflictedException("E1000001", "mobile", "vCode");
            }
            if(user.getvExpire().before(new Date())){
                throw new RequestConflictedException("E0000006", "vCode");
            }
            
            loginTransfer.setUserId(user.getUserId());
        
            if(user.getType().equals(Constant.TYPE_USER_DOC) && !user.getRegisterStatus().equals(Constant.REGISTER_STATUS_ACCESS) ){
                throw new RequestConflictedException("E2000002");
            }
            
            String token = TokenUtils.createToken(user, loginType, createOn.getTime());
            loginTransfer.setToken(token);
            loginTransfer.setRoleList(roleDao.queryListRoleByUserId(user.getUserId()));
            UserProfileEntity userProfile = userProfileDao.queryUserProfileEntity(user.getUserId());
            if(userProfile != null){
                BeanUtils.copyProperties(userProfile, loginTransfer);
                if(user.getType().equals(Constant.TYPE_USER_APP)){
                    String name = userProfile.getName();
                    String addr = userProfile.getAddress();
                    if(StringUtils.isBlank(name) || name.equals(mobile)) {
                        loginTransfer.setName(null);
                        loginTransfer.setNeedChangePassword(true);
                    } else if(StringUtils.isBlank(addr)){
                        loginTransfer.setNeedChangePassword(true);
                    }
                }
            }
            
            loginTransfer.setMobile(user.getMobile());
            
            UserLoginEntity userLoginEntity = new UserLoginEntity();
            userLoginEntity.setUserId(user.getUserId());
            userLoginEntity.setAccount(user.getAccount());
            userLoginEntity.setMobile(user.getMobile());
            userLoginEntity.setSerial(createOn);
            userLoginEntity.setCreateBy(createBy);
            userLoginEntity.setCreateOn(createOn);
            userLoginEntity.setStatus(Constant.VALID);
            userLoginEntity.setVersion(Constant.DEFAULT_VERSION);
            userLoginEntity.setToken(token);
            userLoginDao.insertUserLoginEntity(userLoginEntity);
        } else if(loginType.equals(Constant.LOGIN_TYPE_PWD)){
            loginTransfer.setNeedChangePassword(false);
            String password = loginDto.getPassword();
            if(StringUtils.isBlank(password)){
                throw new RequestConflictedException("E0000001", "passWord");
            }
            
            String account = loginDto.getAccount();
            if(StringUtils.isBlank(account)){
                throw new RequestConflictedException("E1000002", "mobile", "account");
            }
    
            UserEntity user = userDao.queryUserByAccount(loginDto.getType(), account);
            if(user == null){
                user = userDao.queryUserByMobile(loginDto.getType(), account);
            }
        
            if(user == null){
                throw new RequestConflictedException("E0000004", "account");
            }
            loginTransfer.setUserId(user.getUserId());
            if(user.getType().equals(Constant.TYPE_USER_DOC) && !user.getRegisterStatus().equals(Constant.REGISTER_STATUS_ACCESS)){
                throw new RequestConflictedException("E2000002");
            }
            String slatPassword = TokenUtils.makePassword(password, user.getSlat());
            if(!slatPassword.equals(user.getPassword())){
                throw new RequestConflictedException("E0000002", "passWord");
            }
            String token = TokenUtils.createToken(user, loginType, createOn.getTime());
            loginTransfer.setToken(token);
            loginTransfer.setRoleList(roleDao.queryListRoleByUserId(user.getUserId()));
            UserProfileEntity userProfile = userProfileDao.queryUserProfileEntity(user.getUserId());
            if(userProfile != null){
                BeanUtils.copyProperties(userProfile, loginTransfer);
                if(user.getType().equals(Constant.TYPE_USER_APP)){
                    String name = userProfile.getName();
                    String addr = userProfile.getAddress();
                    if(StringUtils.isBlank(name) || name.equals(user.getMobile())) {
                        loginTransfer.setName(null);
                        loginTransfer.setNeedChangePassword(true);
                    } else if(StringUtils.isBlank(addr)){
                        loginTransfer.setNeedChangePassword(true);
                    }
                }
            }
            loginTransfer.setMobile(user.getMobile());
            
            UserLoginEntity userLoginEntity = new UserLoginEntity();
            userLoginEntity.setUserId(user.getUserId());
            userLoginEntity.setAccount(user.getAccount());
            userLoginEntity.setMobile(user.getMobile());
            userLoginEntity.setSerial(createOn);
            userLoginEntity.setCreateBy(createBy);
            userLoginEntity.setCreateOn(createOn);
            userLoginEntity.setStatus(Constant.VALID);
            userLoginEntity.setVersion(Constant.DEFAULT_VERSION);
            userLoginEntity.setToken(token);
            userLoginDao.insertUserLoginEntity(userLoginEntity);
        }
        Long departmentId = loginTransfer.getDepartmentId();
        if(departmentId != null){
            DepartmentEntity department = departmentDao.selectDepartmentEntity(departmentId);
            if(department != null){
                loginTransfer.setDepartmentName(department.getDepartmentName());
            }
        }
        return loginTransfer;
    }
    
    /**
     * 登出
     * @param httpHeaders
     * @return
     */
    public int userLogout(HttpHeaders httpHeaders) {
        int result = -1;
        
        Long userId = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        result += userLoginDao.userLogout(userId);
        
        return result;
    }
    
    /**
     * 删除用户
     * @param headers
     * @param userDto
     * @return
     */
    public int delete(HttpHeaders headers, UserDto userDto) {
        int result = -1;
        Long userId = userDto.getUserId();
        if(userId == null){
            throw new RequestConflictedException("E0000001", "userId");
        }
        result = userDao.deleteUserEntity(userId);
    
        return result;
    }
    
    /**
     * 创建随机账号
     * @param httpHeaders
     * @param prefix
     * @param size
     * @return
     */
    public int createRandomAccount(HttpHeaders httpHeaders, String prefix, Integer size) {
        int result = -1;
        Date createOn = new Date();
        Long createBy = TokenUtils.getUserIdFromToken(TokenUtils.getAuthToken(httpHeaders));
        for(int i=0; i<size; i++){
            RandomEntity randomEntity = new RandomEntity();
            randomEntity.setAccount(prefix + i);
            randomEntity.setUseStatus(Constant.UNKNOWN);
            randomEntity.setCreateOn(createOn);
            randomEntity.setCreateBy(createBy);
            randomEntity.setStatus(Constant.VALID);
            randomEntity.setVersion(Constant.DEFAULT_VERSION);
            result += randomDao.createRandomEntity(randomEntity);
        }
        return result;
    }
    
    /**
     * 创建用户与角色的关系
     * @param roleEntityList
     */
    private int dealWithUserRole(List<RoleEntity> roleEntityList, Long userId, Long createBy, Date createOn){
        int result = 0;
        if(roleEntityList != null && roleEntityList.size() > 0){
            for(RoleEntity role : roleEntityList){
                UserRoleEntity userRole = new UserRoleEntity();
                userRole.setUserId(userId);
                userRole.setRoleId(role.getRoleId());
                userRole.setStatus(Constant.VALID);
                userRole.setVersion(Constant.DEFAULT_VERSION);
                userRole.setCreateBy(createBy);
                userRole.setCreateOn(createOn);
                result += userRoleDao.createUserRoleEntity(userRole);
            }
        }
        return result;
    }
    
    /**
     * 生成随机码
     * @param size 位数
     * @return
     */
    private String createRandomCode(int size){
        String result = "";
        Random random = new Random();
        for(int i=0; i<size; i++){
            result += random.nextInt(10);
        }
        
        return result;
    }
    
    /**
     * 获取不重复账号
     * @return
     */
    private String getRandomAccount(){
        String account = "";
        RandomEntity randomAccount = randomDao.openAccount();
        int n = 10;
        boolean flag = true;
        while (flag && n-- > 0){
            int result = randomDao.closeAccount(randomAccount);
            if(result > 0){
                account = randomAccount.getAccount();
                flag = false;
            } else {
                randomAccount = randomDao.openAccount();
            }
        }
        return account;
    }
}
