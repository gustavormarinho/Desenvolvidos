using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;
using System.Data.OleDb;
    
        
namespace frm_CadFun
{
    class clConexao
    {
        SqlConnection con = new SqlConnection(@"Password=********;Persist Security Info=True;User ID=sa;Initial Catalog=BD_TCC1;Data Source=DESKTOP-BBSC01Q");
        public SqlConnection conectarBD()
        {

            try
            {
                con.Close();
                con.Open();
            }
            catch(Exception e)
            {
                e.Message.ToString();
            }

             return con; 
        }
        public SqlConnection desconectarBD()
        {
            con.Close();
            return con;
        }

    }
}
  
