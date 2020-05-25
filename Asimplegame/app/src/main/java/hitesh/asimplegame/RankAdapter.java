package hitesh.asimplegame;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.util.ArrayList;

public class RankAdapter extends BaseAdapter {
    //Data인 list를 파라미터로 받아 뷰들과 데이터를 바인딩해준다.
    private ArrayList<Rank_item> mList;
    private LayoutInflater mInflater;
    private Context mContext;

    public RankAdapter(Context context, ArrayList<Rank_item> list) {
        this.mList = list;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    //position은 arrayList에 들어갈 아이템의 위치


    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            int res = 0;
            res = R.layout.list_item;
            convertView = mInflater.inflate(res, parent, false);
        }
        int score;
        int rank;

        TextView mrank = (TextView) convertView.findViewById(R.id.rank);
        TextView mscore = (TextView) convertView.findViewById(R.id.score);
        TextView mid = (TextView) convertView.findViewById(R.id.uid);
        LinearLayout layout_view = (LinearLayout) convertView.findViewById(R.id.vi_view);
        rank=mList.get(position).getRank();
        System.out.println(rank);
        mrank.setText(String.valueOf(rank));
        score=mList.get(position).getGrade();
        mscore.setText(String.valueOf(score));
        mid.setText(mList.get(position).getId());
        // Button ok = (Button)convertView.findViewById(R.id.gomain);
        /*	버튼에 이벤트처리를 하기위해선 setTag를 이용해서 사용할 수 있습니다.
         *
         * 	Button btn 가 있다면, btn.setTag(position)을 활용해서 각 버튼들의 이벤트처리를 할 수 있습니다.
         */
       /* ok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //GoIntent(position);
                Intent intent = new Intent(mContext, QuestionActivity.class);
                mContext.startActivity(intent);
                // Toast.makeText(this, "수락되었습니다.", Toast.LENGTH_SHORT).show();
            }
        };*/
        return convertView;
    }


  /*  public void GoIntent(int a){
       Intent intent = new Intent(mContext, MainActivity.class);
        //putExtra 로 선택한 아이템의 정보를 인텐트로 넘겨 줄 수 있다.
        intent.putExtra("TITLE", arr.get(a).Title);
        intent.putExtra("TITLE", arr.get(a).Title);

   }*/
    //Data인 list를 파라미터로 받아 뷰들과 데이터를 바인딩해준다.
}
/*
public class MyReAdapter extends RecyclerView.Adapter<MyReAdapter.MyViewHolder> {

    private ArrayList<Item> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    public MyReAdapter(Context context, ArrayList<Item> list) {
        this.mList = list;
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //포스터만 출력하자.

        holder.quote.setText("\"" + mList.get(position).getQuote()+ "\"");
        holder.quthor.setText("-" + mList.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ;
        public TextView quote;
        public TextView quthor;

        public MyViewHolder(View itemView) {
            super(itemView);

            quote = (TextView) itemView.findViewById(R.id.tv_quote);
            quthor = (TextView) itemView.findViewById(R.id.tv_author);
        }
    }
    */



