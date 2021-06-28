package com.base.webapp.common.session;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class CommSession {

    public static HttpSession getSession(HttpServletRequest req) {
        return req.getSession();
    }

    // Session Check
    public static String checkSession(HttpServletRequest req) {
        HttpSession hSession = getSession(req);
        if (hSession == null || hSession.isNew())
            return "Session Disconnected! Login Plase";
        else if (!checkLogin(req)) {
            return "First Login Plase ";
        } else
            return null;
    }

 
    public static boolean checkLogin(HttpServletRequest req) {
        String userId = getUserId(req);

        System.out.println("Now Session User Id : " + userId);
        if (userId == null || userId.length() == 0) {
            return false;
        }
        else return true;
    }
/*
    public static void setLoginInfo(HttpServletRequest req, LoginInfoVO vo) {
        HttpSession hSession = getSession(req);

        setHDDMSInfo(req, vo);


    }


   
    public static void setConsoleInfo(HttpServletRequest req, LoginInfoVO vo) {
        HttpSession hSession = getSession(req);

        hSession.setAttribute("USERID",);
        
        
    
    
    
    }*/

    public static String getUserInfo(HttpServletRequest req) {
        String userInfo = "";
        try {
            HttpSession hSession = getSession(req);
            userInfo = (String)hSession.getAttribute("userinfo");
        } catch(Exception e) {}
        return userInfo;
    }

    public static String getUserId(HttpServletRequest req) {
        String userId;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            userId =  (String)hSession.getAttribute("USERID");
        } catch(Exception e) {
            return null;
        }

        return userId;
    }

    public static String getName(HttpServletRequest req) {
        String name;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            name =  (String)hSession.getAttribute("NAME");
        } catch(Exception e) {
            return null;
        }

        return name;
    }

    public static String getPassword(HttpServletRequest req) {
        String password;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            password =  (String)hSession.getAttribute("PASSWORD");
        } catch(Exception e) {
            return null;
        }

        return password;
    }

    public static String getGroupCd(HttpServletRequest req) {
        String groupCd;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            groupCd =  (String)hSession.getAttribute("GROUPCD");
        } catch(Exception e) {
            return null;
        }

        return groupCd;
    }

    public static String getUserGroup(HttpServletRequest req) {
        String userGroup;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            userGroup =  (String)hSession.getAttribute("USERGROUP");
        } catch(Exception e) {
            return null;
        }

        return userGroup;
    }

    public static String getDistNo(HttpServletRequest req) {
        String distNo;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            distNo =  (String)hSession.getAttribute("DIST_NO");
        } catch(Exception e) {
            return null;
        }

        return distNo;
    }

    public static String getDealNo(HttpServletRequest req) {
        String dealNo;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            dealNo =  (String)hSession.getAttribute("DEAL_NO");
        } catch(Exception e) {
            return null;
        }

        return dealNo;
    }

    public static String getPMFlag(HttpServletRequest req) {
        String pmFlag;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            pmFlag =  (String)hSession.getAttribute("PM_FLAG");
        } catch(Exception e) {
            return null;
        }

        return pmFlag;
    }

    public static String getSalesManagerFlag(HttpServletRequest req) {
        String salesManagerFlag;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            salesManagerFlag =  (String)hSession.getAttribute("SALES_MANAGER_FLAG");
        } catch(Exception e) {
            return null;
        }

        return salesManagerFlag;
    }

    public static String getServiceManagerFlag(HttpServletRequest req) {
        String serviceManagerFlag;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            serviceManagerFlag =  (String)hSession.getAttribute("SERVICE_MANAGER_FLAG");
        } catch(Exception e) {
            return null;
        }

        return serviceManagerFlag;
    }

    public static String getPartsManagerFlag(HttpServletRequest req) {
        String partsManagerFlag;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            partsManagerFlag =  (String)hSession.getAttribute("PARTS_MANAGER_FLAG");
        } catch(Exception e) {
            return null;
        }

        return partsManagerFlag;
    }

    public static String getPosition(HttpServletRequest req) {
        String position;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            position =  (String)hSession.getAttribute("POSITION");
        } catch(Exception e) {
            return null;
        }

        return position;
    }

    public static String getTelNo(HttpServletRequest req) {
        String telNo;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            telNo =  (String)hSession.getAttribute("TEL_NO");
        } catch(Exception e) {
            return null;
        }

        return telNo;
    }

    public static String getFaxNo(HttpServletRequest req) {
        String faxNo;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            faxNo =  (String)hSession.getAttribute("FAX_NO");
        } catch(Exception e) {
            return null;
        }

        return faxNo;
    }

    public static String getEMail(HttpServletRequest req) {
        String eMail;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            eMail =  (String)hSession.getAttribute("E_MAIL");
        } catch(Exception e) {
            return null;
        }

        return eMail;
    }

    public static String getHitCount(HttpServletRequest req) {
        String hitCount;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            hitCount =  (String)hSession.getAttribute("HIT_COUNT");
        } catch(Exception e) {
            return null;
        }

        return hitCount;
    }

    public static String getActiveFlag(HttpServletRequest req) {
        String activeFlag;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            activeFlag =  (String)hSession.getAttribute("ACTIVE_FLAG");
        } catch(Exception e) {
            return null;
        }

        return activeFlag;
    }

    public static String getRegion(HttpServletRequest req) {
        String activeFlag;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            activeFlag =  (String)hSession.getAttribute("REGION");
        } catch(Exception e) {
            return null;
        }

        return activeFlag;
    }

    public static String getSubRegion(HttpServletRequest req) {
        String activeFlag;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            activeFlag =  (String)hSession.getAttribute("SUB_REGION");
        } catch(Exception e) {
            return null;
        }

        return activeFlag;
    }

    public static String getDealRegion(HttpServletRequest req) {
        String activeFlag;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            activeFlag =  (String)hSession.getAttribute("DEAL_REGION");
        } catch(Exception e) {
            return null;
        }

        return activeFlag;
    }

    public static String getDealSubRegion(HttpServletRequest req) {
        String activeFlag;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            activeFlag =  (String)hSession.getAttribute("DEAL_SUB_REGION");
        } catch(Exception e) {
            return null;
        }

        return activeFlag;
    }

    public static String getNation(HttpServletRequest req) {
        String activeFlag;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            activeFlag =  (String)hSession.getAttribute("NATION");
        } catch(Exception e) {
            return null;
        }

        return activeFlag;
    }

    public static String getLanguage(HttpServletRequest req) {
        String activeFlag;

        try {
            HttpSession hSession = getSession(req);
            if (hSession.isNew()) {
                return null;
            }
            activeFlag =  (String)hSession.getAttribute("LANGUAGE");
        } catch(Exception e) {
            return null;
        }

        return activeFlag;
    }




    public static void invalidSession(HttpServletRequest req) {
        System.out.println("Session Clear !");
        HttpSession hSession = getSession(req);
        hSession.invalidate();
    }

}
