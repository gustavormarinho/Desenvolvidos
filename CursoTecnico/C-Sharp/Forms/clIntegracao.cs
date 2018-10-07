using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;
using System.Windows.Forms;

namespace frm_CadFun
{
    class clIntegracao
    {
        clConexao con = new clConexao();
        public string chave;

        public void inserir_Fun(string nome, string funcao, string rg, string usuario, string cpf, string senha, int nivel)
        {
            SqlCommand cmd = new SqlCommand("insert into tb_fun (nome_fun, funcao_fun, rg_fun, username, cpf_fun, senha, nv_acesso) values(@nome_fun,@funcao_fun,@rg_fun,@username,@cpf_fun,@senha,@nv_acesso)", con.conectarBD());

            cmd.Parameters.Add("@nome_fun", SqlDbType.VarChar).Value = nome;
            cmd.Parameters.Add("@funcao_fun", SqlDbType.VarChar).Value = funcao;
            cmd.Parameters.Add("@rg_fun", SqlDbType.VarChar).Value = rg;
            cmd.Parameters.Add("@username", SqlDbType.VarChar).Value = usuario;
            cmd.Parameters.Add("@cpf_fun", SqlDbType.VarChar).Value = cpf;
            cmd.Parameters.Add("@senha", SqlDbType.VarChar).Value = senha;
            cmd.Parameters.Add("@nv_acesso", SqlDbType.Int).Value = nivel;

            cmd.ExecuteNonQuery();

        }

        

        public void inserir_Forn(string nome,string nomefantas,string cnpj, string cep, string cid, string est, string num, string tel, string email, string logra)
        {                        
            SqlCommand cmd = new SqlCommand("insert into tb_forn values(@nome_forn,@nomefantas_forn,@cnpj_forn,@cep_forn,@cid_forn,@est_forn,@num_forn,@tel_forn,@email_forn,@logra_forn)", con.conectarBD());
            cmd.Parameters.Add("@nome_forn", SqlDbType.VarChar).Value = nome;
            cmd.Parameters.Add("@nomefantas_forn", SqlDbType.VarChar).Value = nomefantas;
            cmd.Parameters.Add("@cnpj_forn", SqlDbType.VarChar).Value = cnpj;
            cmd.Parameters.Add("@cep_forn", SqlDbType.VarChar).Value = cep;
            cmd.Parameters.Add("@cid_forn", SqlDbType.VarChar).Value = cid;
            cmd.Parameters.Add("@est_forn", SqlDbType.VarChar).Value = est;
            cmd.Parameters.Add("@num_forn", SqlDbType.VarChar).Value = num;
            cmd.Parameters.Add("@tel_forn", SqlDbType.VarChar).Value = tel;
            cmd.Parameters.Add("@email_forn", SqlDbType.VarChar).Value = email;
            cmd.Parameters.Add("@logra_forn", SqlDbType.VarChar).Value = logra;

            cmd.ExecuteNonQuery();

        }

        public void inserir_Orc(string dataorc, string cpf, string cnpj, string valor)
        {
           SqlCommand cmd = new SqlCommand("insert into tb_orc (data_orc, cpf_orc, cnpj_orc, valortotal_orc) values(@data_orc,@cpf_orc,@cnpj_orc,@valor_orc)", con.conectarBD());
            //SqlCommand cmd = new SqlCommand("insert into tb_orc (cpf_orc, cnpj_orc, valortotal_orc) values(@cpf_orc,@cnpj_orc,@valor_orc)", con.conectarBD());

           

            cmd.Parameters.Add("@data_orc", SqlDbType.VarChar).Value = dataorc;
            cmd.Parameters.Add("@cpf_orc", SqlDbType.VarChar).Value = cpf;
            cmd.Parameters.Add("@cnpj_orc", SqlDbType.VarChar).Value = cnpj;
            cmd.Parameters.Add("@valor_orc", SqlDbType.Decimal).Value = valor;


            cmd.ExecuteNonQuery();

        }
        public void inserir_Ped( string data_ped, string cnpj_ped, string cpf_ped, string valortotal_ped, string datanf_ped, string numnf_ped, string dataent_ped)
        {
            SqlCommand cmd = new SqlCommand("insert into tb_ped values (@data_ped,@cnpj_ped,@cpf_ped,@valortotal_ped,@datanf_ped,@numnf_ped,@dataent_ped)", con.conectarBD());
            cmd.Parameters.Add("@data_ped", SqlDbType.VarChar).Value = data_ped;
            cmd.Parameters.Add("@cnpj_ped", SqlDbType.VarChar).Value = cnpj_ped;
            cmd.Parameters.Add("@cpf_ped", SqlDbType.VarChar).Value = cpf_ped;
            cmd.Parameters.Add("@valortotal_ped", SqlDbType.Decimal).Value = valortotal_ped;
            cmd.Parameters.Add("@datanf_ped", SqlDbType.VarChar).Value = datanf_ped;
            cmd.Parameters.Add("@numnf_ped", SqlDbType.VarChar).Value = numnf_ped;
            cmd.Parameters.Add("@dataent_ped", SqlDbType.VarChar).Value = dataent_ped;


            cmd.ExecuteNonQuery();

        }
        public void inserir_Saida(string cpf_sd, string solic_sd, string data_sd)
        {
           
            SqlCommand cmd = new SqlCommand("insert into tb_sd values (@cpf_sd,@solic_sd,@data_sd)", con.conectarBD());
            cmd.Parameters.Add("@cpf_sd", SqlDbType.VarChar).Value = cpf_sd;
            cmd.Parameters.Add("@solic_sd", SqlDbType.VarChar).Value = solic_sd;
            cmd.Parameters.Add("@data_sd", SqlDbType.VarChar).Value = data_sd;
            cmd.ExecuteNonQuery();
            con.desconectarBD();
        }
        

        public void inserir_Produto(string nome_prod, string tipo_prod, string qtde_prod, string desc_prod, string local_prod)
        {
            SqlCommand cmd = new SqlCommand("insert into tb_prod values (@nome_prod,@tipo_prod,@qtde_prod,@desc_prod,@local_prod)", con.conectarBD());


            cmd.Parameters.Add("@nome_prod", SqlDbType.VarChar).Value = nome_prod;
            cmd.Parameters.Add("@tipo_prod", SqlDbType.VarChar).Value = tipo_prod;
            cmd.Parameters.Add("@qtde_prod", SqlDbType.VarChar).Value = qtde_prod;
            cmd.Parameters.Add("@desc_prod", SqlDbType.VarChar).Value = desc_prod;
            cmd.Parameters.Add("@local_prod", SqlDbType.VarChar).Value = local_prod;
            
            cmd.ExecuteNonQuery();

        }
        public void inserir_ItemPed(string codprod_itemped, string codped_itemped, string qtde_itemped, string preco_itemped, string precototal_itemped)
        {
            SqlCommand cmd = new SqlCommand("insert into tb_itemped values (@codprod_itemped,@codped_itemped,@qtde_itemped,@preco_itemped,@precototal_itemped)", con.conectarBD());


            cmd.Parameters.Add("@codprod_itemped", SqlDbType.VarChar).Value = codprod_itemped;
            cmd.Parameters.Add("@codped_itemped", SqlDbType.VarChar).Value = codped_itemped;
            cmd.Parameters.Add("@qtde_itemped", SqlDbType.VarChar).Value = qtde_itemped;
            cmd.Parameters.Add("@preco_itemped", SqlDbType.VarChar).Value = preco_itemped;
            cmd.Parameters.Add("@precototal_itemped", SqlDbType.VarChar).Value = precototal_itemped;

            cmd.ExecuteNonQuery();

        }
        public void inserir_ItemOrc(string codprod_itemorc, string codorc_itemorc, string quant_itemorc, string preco_itemorc, string precototal_itemorc)
        {
            SqlCommand cmd = new SqlCommand("Insert into tb_itemorc(codprod_itemorc,codorc_itemorc,qtde_itemorc,preco_itemorc,precototal_itemorc) values (@codprod_itemorc,@codorc_itemorc,@quant_itemorc,@preco_itemorc,@precototal_itemorc)", con.conectarBD());

            cmd.Parameters.Add("@codprod_itemorc", SqlDbType.VarChar).Value = codprod_itemorc;
            cmd.Parameters.Add("@codorc_itemorc", SqlDbType.VarChar).Value = codorc_itemorc;
            cmd.Parameters.Add("@quant_itemorc", SqlDbType.VarChar).Value = quant_itemorc;
            cmd.Parameters.Add("@preco_itemorc", SqlDbType.VarChar).Value = preco_itemorc;
            cmd.Parameters.Add("@precototal_itemorc", SqlDbType.VarChar).Value = precototal_itemorc;

            cmd.ExecuteNonQuery();

        }
        public void inserir_ItemSaida(string codprod_itemsaida, string codsd_itemsaida, string quant_itemsaida)
        {
            SqlCommand cmd = new SqlCommand("insert into tb_itemsaida values (@codprod_itemsaida,@codsd_itemsaida,@qtde_itemsaida)", con.conectarBD());


            cmd.Parameters.Add("@codprod_itemsaida", SqlDbType.VarChar).Value = codprod_itemsaida;
            cmd.Parameters.Add("@codsd_itemsaida", SqlDbType.VarChar).Value = codsd_itemsaida;
            cmd.Parameters.Add("@qtde_itemsaida", SqlDbType.VarChar).Value = quant_itemsaida;
        

            cmd.ExecuteNonQuery();

        }
        public void Excluir_Fun(String cpf)
        {
            int i;
            SqlCommand cmd = new SqlCommand("delete from tb_fun where cpf_fun=@cpf_fun", con.conectarBD());
            cmd.Parameters.Add("@cpf_fun", SqlDbType.VarChar).Value = cpf;
            i = cmd.ExecuteNonQuery();
            if (i > 0)
                MessageBox.Show("Exclusão efetuada com sucesso ! ");
            else
                MessageBox.Show("Não existem dados compatíveis ! ");
        }
        public void Excluir_Prod(String codprod)
        {
            int i;
            SqlCommand cmd = new SqlCommand("delete from tb_prod where cod_prod = @cod_prod", con.conectarBD());
            cmd.Parameters.Add("@cod_prod", SqlDbType.VarChar).Value = codprod;
            i = cmd.ExecuteNonQuery();
            if(i > 0)
                   MessageBox.Show("Exclusão efetuada com sucesso ! ");
            else
                MessageBox.Show("Não existem dados compatíveis ! ");
        }
        public void Excluir_Forn(String cnpj)
        {
            int i;
            SqlCommand cmd = new SqlCommand("delete from tb_forn where cnpj_forn=@cnpj_forn", con.conectarBD());
            cmd.Parameters.Add("@cnpj_forn", SqlDbType.VarChar).Value = cnpj;
            i = cmd.ExecuteNonQuery();
            if (i > 0)
                MessageBox.Show("Exclusão efetuada com sucesso ! ");

            else
                MessageBox.Show("Não existem dados compatíveis ! ");
        }
        public void Excluir_Orc(String cod_orc)
        {
            int i;
            SqlCommand cmd = new SqlCommand("delete from tb_orc where cod_orc=@cod_orc", con.conectarBD());
            cmd.Parameters.Add("@cod_orc", SqlDbType.VarChar).Value = cod_orc;
            i = cmd.ExecuteNonQuery();
            if (i > 0)
                MessageBox.Show("Exclusão efetuada com sucesso ! ");

            else
                MessageBox.Show("Não existem dados compatíveis ! ");
        }
        public void Excluir_Ped(String cod_ped)
        {
            int i;
            SqlCommand cmd = new SqlCommand("delete from tb_ped where cod_ped=@cod_ped", con.conectarBD());
            cmd.Parameters.Add("@cod_ped", SqlDbType.VarChar).Value = cod_ped;
            i = cmd.ExecuteNonQuery();
            if (i > 0)
                MessageBox.Show("Exclusão efetuada com sucesso ! ");

            else
                MessageBox.Show("Não existem dados compatíveis ! ");
        }
        public void Excluir_Sd(String cod_sd)
        {
            int i;
            SqlCommand cmd = new SqlCommand("delete from tb_sd where cod_sd=@cod_sd", con.conectarBD());
            cmd.Parameters.Add("@cod_sd", SqlDbType.VarChar).Value = cod_sd;
            i = cmd.ExecuteNonQuery();
            if (i > 0)
                MessageBox.Show("Exclusão efetuada com sucesso ! ");

            else
                MessageBox.Show("Não existem dados compatíveis ! ");
        }
        public void trazcod_Saida()
        {
            SqlCommand cmd1 = new SqlCommand("Select cod_sd from tb_sd", con.conectarBD());
            SqlDataReader dr = cmd1.ExecuteReader();
            while (dr.Read())
            {
                chave = dr["cod_sd"].ToString();
                chave = (int.Parse(chave) + 1).ToString();
            }

            con.desconectarBD();
        }
        public void trazcoditem_Saida()
        {
            SqlCommand cmd1 = new SqlCommand("Select cod_sd from tb_sd", con.conectarBD());
            SqlDataReader dr = cmd1.ExecuteReader();
            while (dr.Read())
            {
                chave = dr["cod_sd"].ToString();
                chave = (int.Parse(chave)).ToString();
            }

            con.desconectarBD();
        }

        public void trazcodped()
        {
            SqlCommand cmd1 = new SqlCommand("Select cod_ped from tb_ped", con.conectarBD());
            SqlDataReader dr = cmd1.ExecuteReader();
            while (dr.Read())
            {
                chave = dr["cod_ped"].ToString();
                chave = (int.Parse(chave)+ 1).ToString();
            }

            con.desconectarBD();
        }

        public void trazcoditemped()
        {
            SqlCommand cmd1 = new SqlCommand("Select cod_ped from tb_ped", con.conectarBD());
            SqlDataReader dr = cmd1.ExecuteReader();
            while (dr.Read())
            {
                chave = dr["cod_ped"].ToString();
                chave = (int.Parse(chave)).ToString();
            }

            con.desconectarBD();
        }
        public void trazcodorc()
        {
            SqlCommand cmd1 = new SqlCommand("Select cod_orc from tb_orc", con.conectarBD());
            SqlDataReader dr = cmd1.ExecuteReader();
            while (dr.Read())
            {
                chave = dr["cod_orc"].ToString();
                chave = (int.Parse(chave) + 1).ToString();
            }

            con.desconectarBD();
        }

        public void trazcoditemorc()
        {
            SqlCommand cmd1 = new SqlCommand("Select cod_orc from tb_orc", con.conectarBD());
            SqlDataReader dr = cmd1.ExecuteReader();
            while (dr.Read())
            {
                chave = dr["cod_orc"].ToString();
                chave = (int.Parse(chave)).ToString();
            }

            con.desconectarBD();
        }
        public void trazcodprod()
        {
            SqlCommand cmd1 = new SqlCommand("Select cod_prod from tb_prod", con.conectarBD());
            SqlDataReader dr = cmd1.ExecuteReader();
            while (dr.Read())
            {
                chave = dr["cod_prod"].ToString();
                chave = (int.Parse(chave) + 1).ToString();
            }

            con.desconectarBD();
        }
        
   

    }
}
