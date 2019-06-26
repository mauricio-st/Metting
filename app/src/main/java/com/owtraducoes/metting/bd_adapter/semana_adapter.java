package com.owtraducoes.metting.bd_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.owtraducoes.metting.R;
import com.owtraducoes.metting.bd_access.bd_class.semana_class;

import java.util.Calendar;
import java.util.List;

public class semana_adapter extends RecyclerView.Adapter<semana_adapter.viewholder> {

    List<semana_class> semana_dados;

    public semana_adapter(List<semana_class> dados) {
        semana_dados = dados;
    }

    @Override
    public semana_adapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.privilegio_linha, parent, false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(semana_adapter.viewholder holder, int position) {

        Calendar cal = Calendar.getInstance();
        cal.set(1989, 2, 20, 19, 45, 0);

        if (semana_dados.size() > 0) {

            semana_class semana = semana_dados.get(position);


            cal.add(Calendar.HOUR, semana.tempo);

            holder.txt_tempo.setText(cal.HOUR_OF_DAY+":"+cal.MINUTE);
            holder.txt_privilegio.setText(semana.privilegio);
            holder.txt_nome1.setText(semana.nome1);
            holder.txt_nome2.setText(semana.nome2);

        }

    }

    @Override
    public int getItemCount() {
        return semana_dados.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        public TextView txt_tempo;
        public TextView txt_privilegio;
        public TextView txt_nome1;
        public TextView txt_nome2;

        public viewholder(View itemView) {
            super(itemView);

            txt_tempo = itemView.findViewById(R.id.txt_tempo);
            txt_privilegio = itemView.findViewById(R.id.txt_privilegio);
            txt_nome1 = itemView.findViewById(R.id.txt_nome1);
            txt_nome2 = itemView.findViewById(R.id.txt_nome2);

        }
    }

}
