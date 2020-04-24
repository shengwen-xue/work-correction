package com.cxt.work.correction.config;

import com.cxt.work.correction.pojo.TbPermission;
import com.cxt.work.correction.pojo.TbRole;
import com.cxt.work.correction.pojo.TbUser;
import com.cxt.work.correction.pojo.TbUserRole;
import com.cxt.work.correction.service.TbRolePermissionService;
import com.cxt.work.correction.service.TbRoleService;
import com.cxt.work.correction.service.TbUserRoleService;
import com.cxt.work.correction.service.TbUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *     UserRealm配置类
 * </p>
 *
 * @author xue-sheng-wen
 * @date 2020/2/28 13:47
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbUserRoleService tbUserRoleService;

    @Autowired
    private TbRoleService tbRoleService;

    @Autowired
    private TbRolePermissionService tbRolePermissionService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行了->授权");
        try {
            // 给资源进行授权
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 硬编码
            // info.addStringPermission("user:add");
            // 从数据库查询用户的角色和权限：
            // 先获取当前登录用户
            Subject currentLoginUser = SecurityUtils.getSubject();
            TbUser user = (TbUser) currentLoginUser.getPrincipal();
            TbUser dbUser = tbUserService.selectById(user.getId());
            log.info("当前用户：{}", user);
            log.info("数据库用户：{}", dbUser);
            // 根据用户id查询角色
            List<TbUserRole> userRoleList = tbUserRoleService.selectUserRolesByUserId(dbUser.getId());
            Long[] roleIds = userRoleList.stream().map(TbUserRole::getRoleId).toArray(Long[]::new);
            log.info("根据用户id查询的用户角色集合：{}", userRoleList);
            log.info("角色ID数组：{}", roleIds);
            // 根据角色Id查询角色名称集合
            List<TbRole> tbRoleList = tbRoleService.selectRoleNameByRoleIds(roleIds);
            Set<String> roleNames = tbRoleList.stream().map(TbRole::getRoleName).collect(Collectors.toSet());
            log.info("根据角色id数组查询的角色集合：{}", tbRoleList);
            log.info("角色名称Set集合：{}", roleNames);
            // 根据角色Id查询权限名称集合
            List<TbPermission> tbRolePermissionList = tbRolePermissionService.selectPermissionNameByRoleIds(roleIds);
            Set<String> permissionNames = tbRolePermissionList.stream().map(TbPermission::getPermissionName).collect(Collectors.toSet());
            log.info("根据角色id数组查询的权限集合：{}", tbRolePermissionList);
            log.info("权限名称Set集合：{}", permissionNames);
            // 角色集合
            // Set<String> roleNames = new HashSet<String>();
            // 权限集合
            // Set<String> permissionNames = new HashSet<String>();
            info.setRoles(roleNames);
            info.setStringPermissions(permissionNames);
            info.addStringPermission("");
            return info;
        } catch (Exception e) {
            log.error("shiro授权失败", e.getLocalizedMessage());
            e.printStackTrace();
            return null;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        // return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("执行了->认证");
        // 获取当前需要认证的用户
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        // 验证用户名
        TbUser user = tbUserService.selectByUserName(userToken.getUsername());
        // 用户名不存在
        if(Objects.isNull(user)) {
            // shiro底层会抛出UnknownAccountException
            return null;
        }
        // 判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
