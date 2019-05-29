package com.owtraducoes.metting.bd_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.owtraducoes.metting.R;
import com.owtraducoes.metting.bd_access.bd_class.matriculado_class;

import java.util.List;

public class matriculado_adapter extends RecyclerView.Adapter<matriculado_adapter.viewholder_matriculado> {

    List<matriculado_class> dados;

    public matriculado_adapter(List<matriculado_class> matriculado_dados) {
        dados = matriculado_dados;
    }

    @Override
    public viewholder_matriculado onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.matriculados_linha, parent, false);

        viewholder_matriculado holderMatriculado = new viewholder_matriculado(view, parent.getContext());

        return holderMatriculado;
    }

    @Override
    public void onBindViewHolder(viewholder_matriculado holder, int position) {

        if ((dados != null) && (dados.size() > 0)) {

            matriculado_class matriculado = dados.get(position);

            holder.txtnome.setText(matriculado.nome);
            holder.txtgrupo.setText(matriculado.grupo);

        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class viewholder_matriculado extends RecyclerView.ViewHolder {

        public TextView txtnome;
        public TextView txtgrupo;

        public viewholder_matriculado(View itemView, final Context context) {
            super(itemView);

            txtnome  = itemView.findViewById(R.id.txt_nome);
            txtgrupo = itemView.findViewById(R.id.txt_grupo);

        }
    }

}
