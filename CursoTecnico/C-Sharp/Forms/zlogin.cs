using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace frm_CadFun
{
    public partial class zlogin : Form
    {
        public zlogin()
        {
            InitializeComponent();
        }
        public static int nvacesso = 0;
        public static string nome;

        private void btnLogin_Click(object sender, EventArgs e)
        {
            clConexao con = new clConexao();

            SqlCommand comm = new SqlCommand("Select count(*) From tb_fun where username=@usuario And senha=@senha", con.conectarBD());



            comm.Parameters.Add("@usuario", SqlDbType.VarChar).Value = txtLogin.Text;
            comm.Parameters.Add("@senha", SqlDbType.VarChar).Value = txtSen.Text;

            int i = (int)comm.ExecuteScalar();



            if (i == 0)
            {

                MessageBox.Show("Senha Incorreta");
                
            }

            else
            {
                SqlCommand sql = new SqlCommand("select nv_acesso from tb_fun where username=@username", con.conectarBD());
                sql.Parameters.Add("@username", SqlDbType.VarChar).Value = txtLogin.Text;
                string a = sql.ExecuteScalar().ToString();
                nvacesso = int.Parse(a);

                SqlCommand sql2 = new SqlCommand("select nome_fun from tb_fun where username=@username", con.conectarBD());
                sql2.Parameters.Add("@username", SqlDbType.VarChar).Value = txtLogin.Text;
                string b = sql2.ExecuteScalar().ToString();
                nome = b;
                

                Menu c = new Menu();
                c.Show();
                this.Hide();
            }

        }
       
       
    }
}
