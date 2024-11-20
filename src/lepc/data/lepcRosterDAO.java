package lepc.data;
import lepc.to.*;
import lepc.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import main.to.*;
import hs.data.*;

import idhsInspections.data.InspectionsSQL;


public class lepcRosterDAO extends HsDAO{
    public lepcRosterDAO() {
        
    }
  public int insertRoster(lepcRoster roster,String userId) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,lepcSQL.SELECT_NEXT_ROSTER_ID );
     pstmt = conn.prepareStatement(lepcSQL.INSERT_LEPC_ROSTER);
     pstmt.clearParameters();
     pstmt.setString(2,roster.getRosterAddress1());
     pstmt.setString(3,roster.getRosterAddress2());
     pstmt.setString(7,roster.getRosterChairman());
     pstmt.setString(4,roster.getRosterCity());
     pstmt.setString(6,roster.getRosterEmail());
     pstmt.setString(10,roster.getRosterEmerCoor());
     pstmt.setString(13,roster.getRosterFax());
     pstmt.setString(8,roster.getRosterInfoCoor());
     pstmt.setString(11,roster.getRosterLocation());
     pstmt.setString(14,roster.getRosterPhoneAdmin());
     pstmt.setString(15,roster.getRosterPhoneAlt());
     pstmt.setString(12,roster.getRosterPhoneEmer());
     pstmt.setString(9,roster.getRosterPlanCoor());
     pstmt.setString(5,roster.getRosterZip());
     pstmt.setInt(16,roster.getLepcId());
     pstmt.setInt(1,id);
     roster.setRosterId(id);
     pstmt.setString(17,userId);
     pstmt.execute();
     
     conn.commit();
    
     return id;
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
  public void updateRoster(lepcRoster roster) throws SQLException, Exception
    {
     Connection conn = null;
     PreparedStatement pstmt = null;
     
     try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
       
      
       pstmt = conn.prepareStatement(lepcSQL.UPDATE_LEPC_ROSTER);
       pstmt.clearParameters();
       pstmt.setString(18,roster.getRosterAddBy());
       pstmt.setString(2,roster.getRosterAddress1());
       pstmt.setString(3,roster.getRosterAddress2());
       pstmt.setString(7,roster.getRosterChairman());
       pstmt.setString(4,roster.getRosterCity());
       pstmt.setString(1,roster.getRosterDateString());
       pstmt.setString(6,roster.getRosterEmail());
       pstmt.setString(10,roster.getRosterEmerCoor());
       pstmt.setString(13,roster.getRosterFax());
       pstmt.setString(8,roster.getRosterInfoCoor());
       pstmt.setString(11,roster.getRosterLocation());
       pstmt.setString(14,roster.getRosterPhoneAdmin());
       pstmt.setString(15,roster.getRosterPhoneAlt());
       pstmt.setString(12,roster.getRosterPhoneEmer());
       pstmt.setString(9,roster.getRosterPlanCoor());
       pstmt.setString(17,roster.getRosterStatus());
       pstmt.setString(5,roster.getRosterZip());
       pstmt.setInt(16,roster.getLepcId());
       pstmt.setInt(19,roster.getRosterId());
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
   public lepcRoster selectRoster(int rosterId) throws SQLException, Exception 
  {
  
   LepcYear lepc = new LepcYear();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     lepcRoster roster = new lepcRoster();
     conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_ROSTER );
     pstmt.clearParameters();
     pstmt.setInt(1,rosterId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {  
        roster.setRosterAddBy(rs.getString(18));
        roster.setRosterAddress1(rs.getString(2));
        roster.setRosterAddress2(rs.getString(3));
        roster.setRosterChairman(rs.getString(7));
        roster.setRosterCity(rs.getString(4));
        roster.setRosterDate(rs.getDate(19));
        roster.setRosterEmail(rs.getString(6));
        roster.setRosterEmerCoor(rs.getString(10));
        roster.setRosterFax(rs.getString(13));
        roster.setRosterInfoCoor(rs.getString(8));
        roster.setRosterLocation(rs.getString(11));
        roster.setRosterPhoneAdmin(rs.getString(14));
        roster.setRosterPhoneAlt(rs.getString(15));
        roster.setRosterPhoneEmer(rs.getString(12));
        roster.setRosterPlanCoor(rs.getString(9));
        roster.setRosterStatus(rs.getString(17));
        roster.setRosterZip(rs.getString(5));
        roster.setLepcId(rs.getInt(16));
        roster.setRosterId(rs.getInt(1));
   }
    return roster;
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
  public lepcRoster selectRosterByLepc(int lepcId) throws SQLException, Exception 
  {
  
  LepcYear lepc = new LepcYear();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
     
  try 
  {
    lepcRoster roster = new lepcRoster();
    conn = getConnection();
    pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_ROSTER_BY_LEPC);
    pstmt.clearParameters();
    pstmt.setInt(1,lepcId);
    rs = pstmt.executeQuery();
    if(rs.next()) 
    {  
       roster.setRosterAddBy(rs.getString(18));
       roster.setRosterAddress1(rs.getString(2));
       roster.setRosterAddress2(rs.getString(3));
       roster.setRosterChairman(rs.getString(7));
       roster.setRosterCity(rs.getString(4));
       roster.setRosterDate(rs.getDate(19));
       roster.setRosterEmail(rs.getString(6));
       roster.setRosterEmerCoor(rs.getString(10));
       roster.setRosterFax(rs.getString(13));
       roster.setRosterInfoCoor(rs.getString(8));
       roster.setRosterLocation(rs.getString(11));
       roster.setRosterPhoneAdmin(rs.getString(14));
       roster.setRosterPhoneAlt(rs.getString(15));
       roster.setRosterPhoneEmer(rs.getString(12));
       roster.setRosterPlanCoor(rs.getString(9));
       roster.setRosterStatus(rs.getString(17));
       roster.setRosterZip(rs.getString(5));
       roster.setLepcId(rs.getInt(16));
       roster.setRosterId(rs.getInt(1));
       roster.setRosterPersons(this.selectRepPersons(roster.getRosterId()));
  }
   return roster;
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
  
 
  public List selectRepPersons(int rosterId) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
  
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_ROSTER_PERSONS);
     pstmt.clearParameters();
     pstmt.setInt(1,rosterId);
     rs = pstmt.executeQuery();
     while(rs.next()) 
     { lepcRosterRep rep = new lepcRosterRep();
       rep.setPersonFirstName(rs.getString(6));
       rep.setPersonId(rs.getInt(1));
       rep.setRosterId(rs.getInt(2));
       rep.setPersonEmail(rs.getString(4));
       rep.setPersonType(rs.getString(5));
       rep.setPersonMi(rs.getString(7));
       rep.setPersonLastName(rs.getString(3));
       rep.setLepcDate(rs.getDate(16));
       rep.setAddDate(rs.getDate(9));
       rep.setStatusDate(rs.getDate(10));
       rep.setPersonStatus(rs.getString(11));
        rep.setPersonEthicsId(rs.getString(12));
       rep.setPersonEthicsPwd(rs.getString(13));
       rep.setPersonTrainStatus(rs.getString(14));
       rep.setPersonDept(rs.getString(15));
       rep.setRosterProxies(this.selectRepPersonProxyByPerson(rep.getPersonId()));
       list.add(rep);
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
  public List selectRepPersonsAdmin(int rosterId) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
  
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_ROSTER_PERSONS_ADMIN);
     pstmt.clearParameters();
     pstmt.setInt(1,rosterId);
     rs = pstmt.executeQuery();
     while(rs.next()) 
     { lepcRosterRep rep = new lepcRosterRep();
       rep.setPersonFirstName(rs.getString(6));
       rep.setPersonId(rs.getInt(1));
       rep.setRosterId(rs.getInt(2));
       rep.setPersonEmail(rs.getString(4));
       rep.setPersonType(rs.getString(5));
       rep.setPersonMi(rs.getString(7));
       rep.setPersonLastName(rs.getString(3));
       rep.setLepcDate(rs.getDate(16));
       rep.setAddDate(rs.getDate(9));
       rep.setStatusDate(rs.getDate(10));
       rep.setPersonStatus(rs.getString(11));
       rep.setPersonEthicsId(rs.getString(12));
       rep.setPersonEthicsPwd(rs.getString(13));
       rep.setPersonTrainStatus(rs.getString(14));
       rep.setPersonDept(rs.getString(15));
       rep.setRosterProxies(this.selectRepPersonProxyByPerson(rep.getPersonId()));
         list.add(rep);
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
  public int insertRepresentative(lepcRosterRep rep) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,lepcSQL.SELECT_NEXT_PERSON_ID );
       rep.setPersonId(id);
     pstmt = conn.prepareStatement(lepcSQL.INSERT_LEPC_ROSTER_PERSONS);
     pstmt.clearParameters();
     pstmt.setString(8,rep.getLepcDateString());
     pstmt.setString(9,rep.getStatusDateString());
     pstmt.setString(4,rep.getPersonEmail());
     pstmt.setString(11,rep.getPersonEthicsId());
     pstmt.setString(12,rep.getPersonEthicsPwd());
     pstmt.setString(6,rep.getPersonFirstName());
     pstmt.setString(3,rep.getPersonLastName());
     pstmt.setString(7,rep.getPersonMi());
     pstmt.setString(10,"New");
     pstmt.setString(13,rep.getPersonTrainStatus());
     pstmt.setString(5,rep.getPersonType());
     pstmt.setInt(1,id);
     pstmt.setInt(2,rep.getRosterId());
     pstmt.setString(14,rep.getPersonDept());
     pstmt.execute();
     
     conn.commit();
    
     return id;
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
  public lepcRosterRep selectRepresentative(int personId) throws SQLException, Exception 
  {
  
  
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
     
  try 
  {
    lepcRosterRep rep = new lepcRosterRep();
    conn = getConnection();
    pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_ROSTER_PERSON );
    pstmt.clearParameters();
    pstmt.setInt(1,personId);
    rs = pstmt.executeQuery();
    if(rs.next()) 
    {  
    rep.setPersonFirstName(rs.getString(6));
    rep.setPersonId(rs.getInt(1));
    rep.setRosterId(rs.getInt(2));
    rep.setPersonEmail(rs.getString(4));
    rep.setPersonType(rs.getString(5));
    rep.setPersonMi(rs.getString(7));
    rep.setPersonLastName(rs.getString(3));
    rep.setLepcDate(rs.getDate(8));
    rep.setAddDate(rs.getDate(9));
    rep.setStatusDate(rs.getDate(10));
    rep.setPersonStatus(rs.getString(11));
    rep.setPersonEthicsId(rs.getString(12));
    rep.setPersonEthicsPwd(rs.getString(13));
    rep.setPersonTrainStatus(rs.getString(14));
    rep.setPersonDept(rs.getString(15));
  }
   return rep;
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
  public void updateRepresentative(lepcRosterRep rep) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
    
     pstmt = conn.prepareStatement(lepcSQL.UPDATE_LEPC_ROSTER_PERSONS);
     pstmt.clearParameters();
     pstmt.setString(9,rep.getAddDateString());
     pstmt.setString(8,rep.getLepcDateString());
     pstmt.setString(10,rep.getStatusDateString());
     pstmt.setString(4,rep.getPersonEmail());
     pstmt.setString(12,rep.getPersonEthicsId());
     pstmt.setString(13,rep.getPersonEthicsPwd());
     pstmt.setString(6,rep.getPersonFirstName());
     pstmt.setString(3,rep.getPersonLastName());
     pstmt.setString(7,rep.getPersonMi());
     pstmt.setString(11,rep.getPersonStatus());
     pstmt.setString(1,rep.getPersonTrainStatus());
     pstmt.setString(5,rep.getPersonType());
     pstmt.setInt(15,rep.getPersonId());
     pstmt.setString(14,rep.getPersonDept());
     pstmt.setInt(2,rep.getRosterId());
       
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
  public int  checkDuplicatePerson(lepcRosterRep rep) throws SQLException, Exception
  {
  ResultSet rs = null;
  Connection conn = null;
  PreparedStatement pstmt = null;
  int dupCount =0;
  try
  {  conn = getConnection();
   pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_ROSTER_DUPLICATE);
   pstmt.clearParameters();
   pstmt.setString(1,rep.getPersonLastName());
    pstmt.setString(2,rep.getPersonFirstName());   
    pstmt.setString(3,rep.getPersonMi());  
    pstmt.setInt(4,rep.getRosterId());
   rs = pstmt.executeQuery();
   if(rs.next()) 
   {
     dupCount=(rs.getInt(1));
  
   }
  
   return dupCount;
  }
  finally
  {
  try {conn.close();
     rs.close();
     pstmt.close();
   } catch (Exception e) {}
  }
  }
  public int  checkRosterSize(lepcRosterRep rep) throws SQLException, Exception
  {
  ResultSet rs = null;
  Connection conn = null;
  PreparedStatement pstmt = null;
  int rosterCount =0;
  try
  {  conn = getConnection();
   pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_ROSTER_SIZE);
   pstmt.clearParameters();
   pstmt.setInt(1,rep.getRosterId());
    pstmt.setString(2,rep.getPersonType());  
    rs = pstmt.executeQuery();
   if(rs.next()) 
   {
     rosterCount=(rs.getInt(1));
  
   }
  
   return rosterCount;
  }
  finally
  {
  try {conn.close();
     rs.close();
     pstmt.close();
   } catch (Exception e) {}
  }
  }
  public int  checkEthicsTraining(String ethicsDate) throws SQLException, Exception
  {
  ResultSet rs = null;
  Connection conn = null;
  PreparedStatement pstmt = null;
  int rosterCount =0;
  try
  {  conn = getConnection();
   pstmt = conn.prepareStatement(lepcSQL.SELECT_ETHICS_CHECK);
   pstmt.clearParameters();
   pstmt.setString(1,ethicsDate);
    rs = pstmt.executeQuery();
   if(rs.next()) 
   {
     rosterCount=(rs.getInt(1));
  
   }
  
   return rosterCount;
  }
  finally
  {
  try {conn.close();
     rs.close();
     pstmt.close();
   } catch (Exception e) {}
  }
  }
  
  public String  selectEthicsSecurity(String userId) throws SQLException, Exception
  {
  ResultSet rs = null;
  Connection conn = null;
  PreparedStatement pstmt = null;
  String ethicsCheck ="N";
  try
  {  conn = getConnection();
      
   pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_PERSON_ETHICS_SECURITY);
   pstmt.clearParameters();
   pstmt.setString(1,userId);
    rs = pstmt.executeQuery();
   while(rs.next()) 
   {
     ethicsCheck="Y";
   }
  
   return ethicsCheck;
  }
  finally
  {
  try {conn.close();
     rs.close();
     pstmt.close();
   } catch (Exception e) {}
  }
  }
  public List selectRepPersonProxyByPerson(int personId) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
  
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_ROSTER_PERSON_PROXY_BY_PERSON);
     pstmt.clearParameters();
     pstmt.setInt(1,personId);
     rs = pstmt.executeQuery();
     while(rs.next()) 
     { lepcRosterRep rep = new lepcRosterRep();
       rep.setPersonFirstName(rs.getString(6));
       rep.setPersonId(rs.getInt(1));
       rep.setRosterId(rs.getInt(2));
       rep.setPersonEmail(rs.getString(4));
       rep.setPersonType(rs.getString(5));
       rep.setPersonMi(rs.getString(7));
       rep.setPersonLastName(rs.getString(3));
       rep.setLepcDate(rs.getDate(8));
       rep.setAddDate(rs.getDate(9));
       rep.setStatusDate(rs.getDate(10));
       rep.setPersonStatus(rs.getString(11));
        rep.setPersonEthicsId(rs.getString(12));
       rep.setPersonEthicsPwd(rs.getString(13));
       rep.setPersonTrainStatus(rs.getString(14));
       list.add(rep);
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
  public lepcRosterRep selectRepPersonProxy(int proxyId) throws SQLException, Exception 
  {
  
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
    lepcRosterRep rep = new lepcRosterRep();
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_ROSTER_PERSON_PROXY);
     pstmt.clearParameters();
     pstmt.setInt(1,proxyId);
     rs = pstmt.executeQuery();
     while(rs.next()) 
     { 
       rep.setPersonFirstName(rs.getString(6));
       rep.setPersonId(rs.getInt(1));
       rep.setRosterId(rs.getInt(2));
       rep.setPersonEmail(rs.getString(4));
       rep.setPersonType(rs.getString(5));
       rep.setPersonMi(rs.getString(7));
       rep.setPersonLastName(rs.getString(3));
       rep.setLepcDate(rs.getDate(8));
       rep.setAddDate(rs.getDate(9));
       rep.setStatusDate(rs.getDate(10));
       rep.setPersonStatus(rs.getString(11));
       rep.setPersonEthicsId(rs.getString(12));
       rep.setPersonEthicsPwd(rs.getString(13));
       rep.setPersonTrainStatus(rs.getString(14));
     }
     
     return rep;
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
  public int insertRepresentativeProxy(lepcRosterRep rep) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
  try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,lepcSQL.SELECT_NEXT_PERSON_ID );
       rep.setPersonId(id);
     pstmt = conn.prepareStatement(lepcSQL.INSERT_LEPC_ROSTER_PERSON_PROXY);
     pstmt.clearParameters();
     pstmt.setString(8,rep.getLepcDateString());
     pstmt.setString(9,rep.getStatusDateString());
     pstmt.setString(4,rep.getPersonEmail());
     pstmt.setString(11,rep.getPersonEthicsId());
     pstmt.setString(12,rep.getPersonEthicsPwd());
     pstmt.setString(6,rep.getPersonFirstName());
     pstmt.setString(3,rep.getPersonLastName());
     pstmt.setString(7,rep.getPersonMi());
     pstmt.setString(10,"New");
     pstmt.setString(13,rep.getPersonTrainStatus());
     pstmt.setString(5,rep.getPersonType());
     pstmt.setInt(1,id);
     pstmt.setInt(2,rep.getRosterId());
     pstmt.execute();
     
     conn.commit();
    
     return id;
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
  
  public void updateRepresentativeProxy(lepcRosterRep rep) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
    
     pstmt = conn.prepareStatement(lepcSQL.UPDATE_LEPC_ROSTER_PERSON_PROXY);
     pstmt.clearParameters();
     pstmt.setString(9,rep.getAddDateString());
     pstmt.setString(8,rep.getLepcDateString());
     pstmt.setString(10,rep.getStatusDateString());
     pstmt.setString(4,rep.getPersonEmail());
     pstmt.setString(12,rep.getPersonEthicsId());
     pstmt.setString(13,rep.getPersonEthicsPwd());
     pstmt.setString(6,rep.getPersonFirstName());
     pstmt.setString(3,rep.getPersonLastName());
     pstmt.setString(7,rep.getPersonMi());
     pstmt.setString(11,rep.getPersonStatus());
     pstmt.setString(1,rep.getPersonTrainStatus());
     pstmt.setString(5,rep.getPersonType());
     pstmt.setInt(14,rep.getPersonId());
     pstmt.setInt(2,rep.getRosterId());
       
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
  public void copyRoster (String lepcId,String rosterId ,String county) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
     proc = conn.prepareCall("{ call lepc_copy_Previous_Roster(?,?,?) }");
     proc.setInt(1, Integer.parseInt(lepcId));
     proc.setInt(2, Integer.parseInt(rosterId));
     proc.setString(3, county);
     proc.execute();

     conn.commit();
     
   } catch (Exception ex) 
   {
     conn.rollback();
     throw new Exception(ex.getMessage());
   }
   finally 
   {
    try { proc.close();
       conn.close();
     } catch (Exception e) {}
   }
  }
  public  void deletePerson(int personId,String sql)   throws SQLException, Exception 
  {
  Connection conn = null;
  PreparedStatement pstmt = null;
  
  try
  { conn = getConnection();
   pstmt = conn.prepareStatement(sql);
   pstmt.clearParameters();
    pstmt.setInt(1,personId);
   pstmt.execute();
  }
  finally
  {
  try {
     conn.close();
     pstmt.close();
   } catch (Exception e) {}
  }
  }
  public String rosterCheck (int lepcRosterId) throws SQLException, Exception 
  {
   Connection conn = null;
    CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
     proc = conn.prepareCall("{ call lepc_roster_check(?,?) }");
     proc.setInt(1, lepcRosterId);
     proc.registerOutParameter(2,java.sql.Types.VARCHAR);
     proc.execute();

    return(proc.getString(2));
     
   } catch (Exception ex) 
   {
     conn.rollback();
     throw new Exception(ex.getMessage());
   }
   finally 
   {
    try { proc.close();
       conn.close();
       
     } catch (Exception e) {}
   }
  }
  
  public void updateRosterPersons(lepcRoster roster,String appDate) throws SQLException, Exception
    {
     Connection conn = null;
     PreparedStatement pstmt = null;
     
     try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
       
      
       pstmt = conn.prepareStatement(lepcSQL.UPDATE_LEPC_ROSTER_PERSONS_APPROVAL);
       pstmt.clearParameters();
       
       pstmt.setString(1,appDate);
       pstmt.setInt(2,roster.getRosterId());
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
  // last
}
