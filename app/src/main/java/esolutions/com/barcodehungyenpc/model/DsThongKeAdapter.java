package esolutions.com.barcodehungyenpc.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import esolutions.com.barcodehungyenpc.R;
import esolutions.com.barcodehungyenpc.entity.CongToGuiKDProxy;
import esolutions.com.barcodehungyenpc.entity.CongToPBProxy;
import esolutions.com.barcodehungyenpc.entity.ThongKe;
import esolutions.com.barcodehungyenpc.utils.Common;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class DsThongKeAdapter extends RecyclerView.Adapter<DsThongKeAdapter.DsThongKeViewHolder> {
    private Context mContext;
    private List<ThongKe> mListThongKe;
    private Common.KIEU_CHUONG_TRINH mKieuChuongTrinh;

    public DsThongKeAdapter(Context mContext, List<ThongKe> mListThongKe, Common.KIEU_CHUONG_TRINH kieuChuongTrinh) throws Exception {
        this.mContext = mContext;
        this.mListThongKe = new ArrayList<>();
        this.mListThongKe.addAll(mListThongKe);
        this.mKieuChuongTrinh = kieuChuongTrinh;
    }

    @Override
    public DsThongKeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.row_rv_ds_thongke, parent, false);
        return new DsThongKeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DsThongKeViewHolder holder, int position) {
        Common.runAnimationClickView(holder.view, R.anim.twinking_view, Common.TIME_DELAY_ANIM);

        holder.tvSTTthongKe.setText(position + 1 + "");

        holder.tvSoBBanTamThoi.setText(mListThongKe.get(position).getmSoBBanTamThoi());
        holder.tvCountCto.setText(mListThongKe.get(position).getmSoLuongCto()+"");
    }

    @Override
    public int getItemCount() {
        return mListThongKe.size();
    }

//    public boolean isDsCtoGhim() {
//        return isDsCtoGhim;
//    }

    public class DsThongKeViewHolder extends RecyclerView.ViewHolder {
        TextView tvSoBBanTamThoi, tvCountCto, tvSTTthongKe;
        View view;
        public DsThongKeViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tvSoBBanTamThoi = (TextView) itemView.findViewById(R.id.tv_so_bienban_tamthoi);
            tvCountCto = (TextView) itemView.findViewById(R.id.tv_count_so_cto_thongKe);
            tvSTTthongKe = (TextView) itemView.findViewById(R.id.tv_STT_thongKe);
        }

    }


    public void refresh(List<ThongKe> thongKeList) {
        if (thongKeList == null)
            return;
        mListThongKe.clear();
        mListThongKe.addAll(thongKeList);
        notifyDataSetChanged();
    }

}
