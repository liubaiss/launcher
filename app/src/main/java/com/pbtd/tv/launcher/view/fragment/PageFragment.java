package com.pbtd.tv.launcher.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pbtd.tv.launcher.R;
import com.pbtd.tv.launcher.bean.PageItemEntity;
import com.pbtd.tv.launcher.utils.AppStartUtil;
import com.pbtd.tv.launcher.widget.JumpCallback;
import com.pbtd.tv.launcher.widget.PageItemView;
import com.pbtd.tv.launcher.widget.PageLayout;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SS on 2017/6/14.
 */

public class PageFragment extends BaseFragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_ID = "ARG_ID";

    private String mPage;
    private PageLayout pageLayout;
    private TextView timeText;
    private ImageView wifiImg;
    private RelativeLayout baseLayout;
    private View view;
    private Gson gson;
    private List<Integer> ids;
    private static Map<Integer, Integer> map;
    private int id;
    private PageItemEntity pageItemEntity;

    public static PageFragment newInstance(int id, PageItemEntity pageItemEntity) {
        Bundle args = new Bundle();
        map = new HashMap<>();
//        args.putString(ARG_PAGE, pageItemEntity);
        args.putSerializable(ARG_PAGE, pageItemEntity);
        args.putInt(ARG_ID,id);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);

//        Log.d(s+"----"+id,"newInstance");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getString(ARG_PAGE);
        pageItemEntity = (PageItemEntity) getArguments().getSerializable(ARG_PAGE);
        id = getArguments().getInt(ARG_ID);
        gson = new Gson();
//        Log.d(mPage+"----"+id+"","oncreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_page, container, false);

        baseLayout = (RelativeLayout) view.findViewById(R.id.launcher_base_layout);

        initView(pageItemEntity);
//        test(pageItemEntity);

        return view;

    }

    private void test(PageItemEntity pageItemEntity) {
        TextView tv = new TextView(getActivity());
        tv.setText(id+"-----"+pageItemEntity);
        tv.setTextColor(Color.WHITE);
        baseLayout.addView(tv);
    }

    private void initView(PageItemEntity pageItemEntity) {
        try {
            createPageLayout(pageItemEntity);
        }catch (Exception e){
            Toast.makeText(getContext(),"运营位数据错误在第"+(id+1)+"个",Toast.LENGTH_SHORT).show();
        }
    }

    private void createPageLayout(final PageItemEntity pageItemEntity) {
        pageLayout = new PageLayout(getActivity(), pageItemEntity, 0, new JumpCallback() {
            //            @Override
            public void onClick(PageItemEntity.DataBean data) {
                if (data.type==2||data.type==0||data.type==1){
                    //标示
                    PageItemView.videoView.stopPlayback();
                    String packageName = data.packageName;
                    String className =  data.className;

                    AppStartUtil.startApp(getActivity(),packageName,className,data);
//                    getActivity().finish();

                }
            }
        });
        baseLayout.addView(pageLayout);

    }

    /**
     * 按行读取txt
     *
     * @param is
     * @return
     * @throws Exception
     */
    private String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }

}
