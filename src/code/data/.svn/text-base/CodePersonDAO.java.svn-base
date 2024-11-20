package code.data;
import code.to.*;
import code.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;
public class CodePersonDAO extends HsDAO
{
  public CodePersonDAO()
  {
   super();
  }
  public CodePersonDAO(HsDataSource pool)
  {
      super(pool);
   }
   
    protected static List selectPersons(Connection conn,int companyId, String personType,CodeManufacturer manufacturer,CodeFacility facility) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    { 
      if (personType.equals("M"))
      {
      pstmt = conn.prepareStatement(CodeSQL.SELECT_PERSON_MANUFACTURER);
      }
      else if (personType.equals("F"))
      { 
      pstmt = conn.prepareStatement(CodeSQL.SELECT_PERSON_FACILITY);
      }
      pstmt.clearParameters();
      pstmt.setInt(1,companyId);
       rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        CodePerson person = new CodePerson();
        person.setCodePersonId(rs.getInt(1));
        person.setCodePersonType(rs.getString(2));
        person.setCodePersonLastName(rs.getString(3));
        person.setCodePersonFirstName(rs.getString(4));
        person.setCodePersonMiddleName(rs.getString(5));
        person.setCodePersonTitle(rs.getString(6));
        person.setCodePersonTelephone(rs.getString(7));
        person.setCodePersonFax(rs.getString(8));
        person.setCodePersonEmail(rs.getString(9));
        
      list.add(person);
      if (personType.equals("M")){
      manufacturer.addPerson(person);
      }
       if (personType.equals("F")){
      facility.addPerson(person);
      }
      }
      
      return list;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
  
}
