package FireBldEducation.data;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
import FireBldEducation.to.*;
public class FireBldEducationCourseDAO extends HsDAO{
    public FireBldEducationCourseDAO() {
    }
    public List selectCourseList(String sql) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      
      try 
      {
     
        conn = getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.clearParameters();
       
        rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          FireBldEducationCourse codeCourse = new FireBldEducationCourse();
            codeCourse.setStartDate(rs.getDate(2));
            codeCourse.setAddress1(rs.getString(5));
            codeCourse.setAddress2(rs.getString(6));
            codeCourse.setCity(rs.getString(7));
            codeCourse.setClassLocation(rs.getString(11));
            codeCourse.setCounty(rs.getString(10));
            codeCourse.setCourseDescription(rs.getString(12));
            codeCourse.setCourseLength(rs.getString(3));
            codeCourse.setState(rs.getString(14));
            codeCourse.setZip(rs.getString(8));
            codeCourse.setZip2(rs.getString(9));
            codeCourse.setClassSize(rs.getInt(13));
            codeCourse.setCourseId(rs.getInt(1));
            codeCourse.setCourseName(rs.getString(4)); 
           /* if(rs.getInt(15) <=0) {
                codeCourse.setClassVacancy(0);
            }
            else
            { */
              int regCount =this.selectCourseRegCount(rs.getInt(1));
              codeCourse.setClassVacancy(rs.getInt(13)-regCount);
            
          list.add(codeCourse);
        }
        return list;
      }
      finally 
      {
       try {
          conn.close();
          rs.close();
          pstmt.close();
        } catch (Exception e) {}
      }
    }
    public FireBldEducationCourse selectCourse(int courseId) throws SQLException, Exception 
    {
       FireBldEducationCourse codeCourse = new FireBldEducationCourse();
      Connection conn = null;
       
      try 
      {
        conn = getConnection();
        codeCourse = selectCourse(conn,courseId);
        return codeCourse;
      }
      finally 
      {
       try {
          conn.close();
         } catch (Exception e) {}
      }
    }
    public FireBldEducationCourse selectCourse(Connection conn,int courseId) throws SQLException, Exception 
      {
        FireBldEducationCourse codeCourse = new FireBldEducationCourse();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        
         try 
        {
          pstmt = conn.prepareStatement(FireBldEducationSQL.SELECT_CODE_COURSE_BY_ID);
          pstmt.clearParameters();
          pstmt.setInt(1,courseId);
          rs = pstmt.executeQuery();
          if(rs.next()) 
          {
              codeCourse.setStartDate(rs.getDate(2));
              codeCourse.setAddress1(rs.getString(5));
              codeCourse.setAddress2(rs.getString(6));
              codeCourse.setCity(rs.getString(7));
              codeCourse.setClassLocation(rs.getString(11));
              codeCourse.setCounty(rs.getString(10));
              codeCourse.setCourseDescription(rs.getString(12));
              codeCourse.setCourseLength(rs.getString(3));
              codeCourse.setState(rs.getString(14));
              codeCourse.setZip(rs.getString(8));
              codeCourse.setZip2(rs.getString(9));
              codeCourse.setClassSize(rs.getInt(13));
              codeCourse.setCourseId(rs.getInt(1));
              codeCourse.setCourseName(rs.getString(4)); 
            codeCourse.setCourseHours(rs.getString(15)); 
          }
            return codeCourse;
        }
        finally 
        {
         try {
            rs.close();
            pstmt.close();
          } catch (Exception e) {}
        }
      }
    public void updateCourse(FireBldEducationCourse codeCourse) throws SQLException, Exception 
     {
             Connection conn = null;
             PreparedStatement pstmt = null;
          try 
       {
             conn = getConnection();
             conn.setAutoCommit(true);
             pstmt = conn.prepareStatement(FireBldEducationSQL.UPDATE_CODE_COURSE_BY_ID);
             pstmt.clearParameters();
           pstmt.setString(1,codeCourse.getStartDateString());
           pstmt.setString(4,codeCourse.getAddress1());
           pstmt.setString(5,codeCourse.getAddress2());
           pstmt.setString(6,codeCourse.getCity());
           pstmt.setString(10,codeCourse.getClassLocation());
           pstmt.setString(9,codeCourse.getCounty());
           pstmt.setString(11,codeCourse.getCourseDescription());
           pstmt.setString(2,codeCourse.getCourseLength());
           pstmt.setString(3,codeCourse.getCourseName());
           pstmt.setString(13,codeCourse.getState());
           pstmt.setString(7,codeCourse.getZip());
           pstmt.setString(8,codeCourse.getZip2());
           pstmt.setInt(12,codeCourse.getClassSize());
           pstmt.setInt(15,codeCourse.getCourseId());
           pstmt.setString(14,codeCourse.getCourseHours());
             pstmt.execute();
              conn.commit();
         
       } catch (Exception ex)
       {
         conn.rollback();
         throw new Exception(ex.getMessage());
       }
       finally 
       {
        try {
           conn.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     } 
      public void insertCourse(FireBldEducationCourse codeCourse) throws SQLException, Exception 
     {
     
             Connection conn = null;
             PreparedStatement pstmt = null;
          try 
       {
             conn = getConnection();
             conn.setAutoCommit(true);
             int id = this.getId(conn,"select code_edu_course_id.nextval from dual");
             pstmt = conn.prepareStatement(FireBldEducationSQL.INSERT_CODE_COURSE);
              pstmt.clearParameters();
         
           pstmt.setString(1,codeCourse.getStartDateString());
           pstmt.setString(4,codeCourse.getAddress1());
           pstmt.setString(5,codeCourse.getAddress2());
           pstmt.setString(6,codeCourse.getCity());
           pstmt.setString(10,codeCourse.getClassLocation());
           pstmt.setString(9,codeCourse.getCounty());
           pstmt.setString(11,codeCourse.getCourseDescription());
           pstmt.setString(2,codeCourse.getCourseLength());
           pstmt.setString(3,codeCourse.getCourseName());
           pstmt.setString(13,codeCourse.getState());
           pstmt.setString(7,codeCourse.getZip());
           pstmt.setString(8,codeCourse.getZip2());
           pstmt.setInt(12,codeCourse.getClassSize());
           pstmt.setInt(14,id);
           pstmt.setString(15,codeCourse.getCourseHours());
           codeCourse.setCourseId(id);
             pstmt.execute();
              conn.commit();
         
       } catch (Exception ex)
       {
         conn.rollback();
         throw new Exception(ex.getMessage());
       }
       finally 
       {
        try {
           conn.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     } 
    public void insertCourseCalender(FireBldEducationCourse codeCourse) throws SQLException, Exception 
     {
             Connection conn = null;
             PreparedStatement pstmt = null;
          try 
       {
             conn = getConnection();
             conn.setAutoCommit(true);
              pstmt = conn.prepareStatement(FireBldEducationSQL.INSERT_DHS_CALENDER);
             pstmt.clearParameters();
             pstmt.setString(5,codeCourse.getStartDateString());
             pstmt.setInt(6,codeCourse.getClassSize());
             pstmt.setString(2,codeCourse.getCourseName());
             pstmt.setString(1,Integer.toString(codeCourse.getCourseId()));
             pstmt.setString(4,codeCourse.getCity());
             pstmt.setString(3,codeCourse.getClassLocation());
             pstmt.setString(7,"course timings:"+codeCourse.getCourseLength()+";Course Details:"+codeCourse.getCourseDescription() +" for details use link https://oas.dhs.in.gov/dfbs/fireBldEducation/course.do?method=courseList");
             
              pstmt.execute();
              conn.commit();
         
       } catch (Exception ex)
       {
         conn.rollback();
         throw new Exception(ex.getMessage());
       }
       finally 
       {
        try {
           conn.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     } 
     
    public void registerCourse(int personId, int courseId) throws SQLException, Exception 
    {
    
           Connection conn = null;
           PreparedStatement pstmt = null;
         try 
     {
           conn = getConnection();
           conn.setAutoCommit(true);
           int id = this.getId(conn,"select CODE_EDU_course_register_id.nextval from dual");
           pstmt = conn.prepareStatement(FireBldEducationSQL.INSERT_CODE_COURSE_USER);
            pstmt.clearParameters();
         pstmt.setInt(1,id);
         pstmt.setInt(2,personId);
         pstmt.setInt(3,courseId);
        
           pstmt.execute();
            conn.commit();
     } catch (Exception ex)
     {
       conn.rollback();
       throw new Exception(ex.getMessage());
     }
     finally 
     {
      try {
         conn.close();
         pstmt.close();
       } catch (Exception e) {}
     }
    }
    public List selectCourseList(String psql,String byLetter) throws SQLException, Exception 
     {
     
       List list = new ArrayList();
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       
       try 
       {
      
         conn = getConnection();
         pstmt = conn.prepareStatement(psql);
         pstmt.clearParameters();
         if(byLetter != null) {
           pstmt.setString(1,byLetter);
         }
         rs = pstmt.executeQuery();
         while(rs.next()) 
         {
             FireBldEducationCourse codeCourse = new FireBldEducationCourse();
               codeCourse.setStartDate(rs.getDate(2));
               codeCourse.setAddress1(rs.getString(5));
               codeCourse.setAddress2(rs.getString(6));
               codeCourse.setCity(rs.getString(7));
               codeCourse.setClassLocation(rs.getString(11));
               codeCourse.setCounty(rs.getString(10));
               codeCourse.setCourseDescription(rs.getString(12));
               codeCourse.setCourseLength(rs.getString(3));
               codeCourse.setState(rs.getString(14));
               codeCourse.setZip(rs.getString(8));
               codeCourse.setZip2(rs.getString(9));
               codeCourse.setClassSize(rs.getInt(13));
               codeCourse.setCourseId(rs.getInt(1));
               codeCourse.setCourseName(rs.getString(4)); 
             int regCount =this.selectCourseRegCount(rs.getInt(1));
             codeCourse.setClassVacancy(rs.getInt(13)-regCount);
             list.add(codeCourse);
         }
         
         return list;
       }
       finally 
       {
        try {
           conn.close();
           rs.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     } 
   
    
      
    public List selectCourseListCancel(String sql,int personId) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      
      try 
      {
     
        conn = getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.clearParameters();
        pstmt.setInt(1,personId);
        rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          FireBldEducationCourse codeCourse = new FireBldEducationCourse();
            codeCourse.setStartDate(rs.getDate(2));
            codeCourse.setAddress1(rs.getString(5));
            codeCourse.setAddress2(rs.getString(6));
            codeCourse.setCity(rs.getString(7));
            codeCourse.setClassLocation(rs.getString(11));
            codeCourse.setCounty(rs.getString(10));
            codeCourse.setCourseDescription(rs.getString(12));
            codeCourse.setCourseLength(rs.getString(3));
            codeCourse.setState(rs.getString(14));
            codeCourse.setZip(rs.getString(8));
            codeCourse.setZip2(rs.getString(9));
            codeCourse.setClassSize(rs.getInt(13));
            codeCourse.setCourseId(rs.getInt(1));
            codeCourse.setCourseName(rs.getString(4)); 
          
          list.add(codeCourse);
        }
        return list;
      }
      finally 
      {
       try {
          conn.close();
          rs.close();
          pstmt.close();
        } catch (Exception e) {}
      }
    }
    public void deletePersonCourse(String courseSQL,int personId, int courseId) throws SQLException, Exception 
    {
    
           Connection conn = null;
           PreparedStatement pstmt = null;
        try 
     {
           conn = getConnection();
           conn.setAutoCommit(true);
            pstmt = conn.prepareStatement(courseSQL);
           pstmt.clearParameters();
         pstmt.setInt(2,personId);
         pstmt.setInt(1,courseId);
        
           pstmt.execute();
            conn.commit();
     } catch (Exception ex)
     {
       conn.rollback();
       throw new Exception(ex.getMessage());
     }
     finally 
     {
      try {
         conn.close();
         pstmt.close();
       } catch (Exception e) {}
     }
    }
    public void updateCourseCalender(FireBldEducationCourse codeCourse) throws SQLException, Exception 
     {
             Connection conn = null;
             PreparedStatement pstmt = null;
          try 
       {
             conn = getConnection();
             conn.setAutoCommit(true);
              pstmt = conn.prepareStatement(FireBldEducationSQL.UPDATE_DHS_CALENDER);
             pstmt.clearParameters();
             pstmt.setString(4,codeCourse.getStartDateString());
             pstmt.setInt(5,codeCourse.getClassSize());
             pstmt.setString(1,codeCourse.getCourseName());
             pstmt.setString(7,Integer.toString(codeCourse.getCourseId()));
             pstmt.setString(3,codeCourse.getCity()+":"+codeCourse.getCounty());
             pstmt.setString(2,codeCourse.getClassLocation());
             pstmt.setString(6,codeCourse.getCourseDescription() +" for details use link https://oas.dhs.in.gov/dfbs/FireBldEducation/start.do");
              pstmt.execute();
              conn.commit();
         
       } catch (Exception ex)
       {
         conn.rollback();
         throw new Exception(ex.getMessage());
       }
       finally 
       {
        try {
           conn.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     } 
    public int selectCourseRegCount(int courseId) throws SQLException, Exception 
    {
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
       int regCount=0;
      
        try 
        {
        conn = getConnection();
         pstmt = conn.prepareStatement(FireBldEducationSQL.COUNT_CODE_COURSE_USER);
         pstmt.clearParameters();
         pstmt.setInt(1,courseId);
         rs = pstmt.executeQuery();
         if(rs.next()) 
         {
             regCount=rs.getInt(1);
             
         }
           return regCount;
        }
      finally 
      {
       try {
          conn.close();
          rs.close();
          pstmt.close();
        } catch (Exception e) {
        return regCount;}
      }
    }
    public int selectPersonRegCount(int personId,int courseId) throws SQLException, Exception 
    {
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      int regCount=0;
      
        try 
        {
        conn = getConnection();
         pstmt = conn.prepareStatement(FireBldEducationSQL.VERIFY_CODE_COURSE_USER);
         pstmt.clearParameters();
         pstmt.setInt(1,courseId);
         pstmt.setInt(2,personId);
         rs = pstmt.executeQuery();
         if(rs.next()) 
         {
             regCount=rs.getInt(1);
             
         }
           return regCount;
        }
      finally 
      {
       try {
          conn.close();
          rs.close();
          pstmt.close();
        } catch (Exception e) {
        return regCount;}
      }
    }
     
    public List selectCourseRoster(int courseId) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      
      try 
      {
     
        conn = getConnection();
        pstmt = conn.prepareStatement(FireBldEducationSQL.SELECT_CODE_COURSE_ROSTER);
        pstmt.clearParameters();
        pstmt.setInt(1,courseId);
        rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
            FireBldEducationUser codeUser = new FireBldEducationUser();
            codeUser.setUserFirstName(rs.getString(2));
            codeUser.setUserId(rs.getString(4));
            codeUser.setUserLastName(rs.getString(1));
            codeUser.setUserPhone(rs.getString(3));
            codeUser.setUserStatus(rs.getString(5));
            codeUser.setUserPersonId(rs.getInt(6));
          list.add(codeUser);
        }
        return list;
      }
      finally 
      {
       try {
          conn.close();
          rs.close();
          pstmt.close();
        } catch (Exception e) {}
      }
    }  
    public void updateCourseRoster(String status,int courseId, int codePersonId) throws SQLException, Exception 
     {
             Connection conn = null;
             PreparedStatement pstmt = null;
          try 
       {
             conn = getConnection();
             conn.setAutoCommit(true);
             pstmt = conn.prepareStatement(FireBldEducationSQL.UPDATE_CODE_COURSE_USER);
             pstmt.clearParameters();
           pstmt.setInt(2,codePersonId);
           pstmt.setInt(3, courseId);
           pstmt.setString(1, status);
             pstmt.execute();
              conn.commit();
         
       } catch (Exception ex)
       {
         conn.rollback();
         throw new Exception(ex.getMessage());
       }
       finally 
       {
        try {
           conn.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     } 
}
