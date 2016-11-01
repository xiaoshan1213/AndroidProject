package com.example.sam.catchcrazycat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Vector;


/**
 * Created by sam on 2016/2/18.
 */
public class Playground extends SurfaceView implements View.OnTouchListener{

    private static  int WIDTH=40;
    private static final int COL=10;
    private static final int ROW=10;
    private static final int BLOCKS=15;
    private Dot matrix[][];
    private Dot cat;
    int k=1;

    public Playground(Context context) {
        super(context);
        getHolder().addCallback(callback);
        matrix=new Dot[ROW][COL];
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++){
                matrix[i][j]=new Dot(j,i);
                //matrix[i][j].setStatus(Dot.STATUS_OFF);
            }
        }
        setOnTouchListener(this);
        initGame();
        //cat.setStatus(Dot.STATUS_IN);

    }

    private void initGame(){
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++){
                matrix[i][j].setStatus(Dot.STATUS_OFF);
            }
        }
        cat=new Dot(4,5);
        getDot(4,5).setStatus(Dot.STATUS_IN);

        for(int i=0;i<BLOCKS;){
            int x= (int) ((Math.random()*10)%COL);
            int y= (int) ((Math.random()*10)%ROW);
           // System.out.println(matrix[x][y].getStatus());
            if(getDot(x,y).getStatus()==Dot.STATUS_OFF){
                getDot(x,y).setStatus(Dot.STATUS_ON);
                i++;
                //System.out.println("block"+i);
            }
        }
    }

    private Dot getDot(int x,int y){
        return matrix[y][x];
    }

    private boolean isAtEdge(Dot d){
        if(d.getX()*d.getY()==0 || d.getX()+1==COL || d.getY()+1==ROW){
            return true;
        }
        return false;
    }

    private Dot getNeighbor(Dot dot,int dir){
        switch (dir){
            case 1:
                return getDot(dot.getX()-1,dot.getY());
            case 2:
                if(dot.getY()%2==0){
                    return getDot(dot.getX()-1,dot.getY()-1);
                }else{
                    return getDot(dot.getX(),dot.getY()-1);
                }
            case 3:
                if(dot.getY()%2==0){
                    return getDot(dot.getX(),dot.getY()-1);
                }else{
                    return getDot(dot.getX()+1,dot.getY()-1);
                }
            case 4:
                return getDot(dot.getX()+1,dot.getY());

            case 5:
                if(dot.getY()%2==0){
                    return getDot(dot.getX(),dot.getY()+1);
                }else{
                    return getDot(dot.getX()+1,dot.getY()+1);
                }

            case 6:
                if(dot.getY()%2==0){
                    return getDot(dot.getX()-1,dot.getY()+1);
                }else{
                    return getDot(dot.getX(),dot.getY()+1);
                }
            default:
                break;

        }
        return null;
    }

    private int getDistance(Dot dot,int dir){
        int distance=0;
        Dot ori=dot,next;
        if(isAtEdge(dot)){
            return 1;
        }
        while (true){
            next=getNeighbor(ori,dir);
            if(next.getStatus()==Dot.STATUS_ON){
                distance++;
                return distance*-1;
            }
            if(isAtEdge(next)){
                distance++;
                return distance;
            }
            distance++;
            ori=next;
        }
    }

    private void moveTo(Dot dot){
        dot.setStatus(Dot.STATUS_IN);
        getDot(cat.getX(),cat.getY()).setStatus(Dot.STATUS_OFF);
        cat.setXY(dot.getX(),dot.getY());
    }

    private void move(){
        if(isAtEdge(cat)){
            lose();
            return;
        }
        Vector<Dot> available=new Vector<>();
        Vector<Dot> positive=new Vector<>();
        HashMap<Dot,Integer> pl=new HashMap<Dot,Integer>();
        for(int i=1;i<7;i++){
            Dot n=getNeighbor(cat,i);
            pl.put(n,i);
            if(n.getStatus()==Dot.STATUS_OFF){
                available.add(n);
                if(getDistance(n,i)>0){
                    positive.add(n);
                }
            }
        }
        System.out.println("availableSize:"+available.size());
        if(available.size()==0){
            win();
        }else if (available.size()==1){
            moveTo(available.get(0));
        }else{
//            int ran= (int) (Math.random()*100%available.size());
//            moveTo(available.get(ran));
            Dot bestNext=null;
            if(positive.size()!=0){
                System.out.println("前进");
                int minL=1000;
                for(int i=0;i<positive.size();i++){
                    int a=getDistance(positive.get(i),pl.get(positive.get(i)));
                    if(a<minL){
                        minL=a;
                        bestNext=positive.get(i);
                    }
                }

            }else{
                System.out.println("躲路障");
                int maxL=0;
                for(int i=0;i<available.size();i++){
                    int k=getDistance(available.get(i),pl.get(available.get(i)));
                    System.out.println("tempL:"+k);
                    if(k<maxL){
                        maxL=k;
                        bestNext=available.get(i);
                    }
                }
            }
            moveTo(bestNext);
        }
    }

    private void lose(){
        Toast.makeText(getContext(),"you lose",Toast.LENGTH_SHORT).show();
    }

    private void win(){
        Toast.makeText(getContext(),"you win",Toast.LENGTH_SHORT).show();
    }

    private void redraw(){
        Canvas canvas=getHolder().lockCanvas();
        canvas.drawColor(Color.LTGRAY);
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        for(int i=0;i<ROW;i++){
            int offset=0;
            if(i%2!=0){
                offset=WIDTH/2;
            }
            for(int j=0;j<COL;j++){
                Dot temp=getDot(j,i);
                switch (temp.getStatus()){
                    case Dot.STATUS_OFF:
                        paint.setColor(0xFFEEEEEE);
                        break;
                    case Dot.STATUS_ON:
                        paint.setColor(0xFFFFAA00);
                        break;
                    case Dot.STATUS_IN:
                        paint.setColor(0xFFFF0000);
                }
                canvas.drawOval(new RectF(temp.getX()*WIDTH+offset,temp.getY()*WIDTH,(temp.getX()+1)*WIDTH+offset,(temp.getY()+1)*WIDTH),paint);
            }

        }
        getHolder().unlockCanvasAndPost(canvas);
    }

    SurfaceHolder.Callback callback=new SurfaceHolder.Callback(){


        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            redraw();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            WIDTH=width/(COL+1);
            redraw();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_UP){
            int x,y;
            y= (int) (event.getY()/WIDTH);
            if(y%2==0){
                x= (int) (event.getX()/WIDTH);
            }else {
                x= (int) ((event.getX()-WIDTH/2)/WIDTH);
            }
            if(x+1>COL || y+1>ROW){
                initGame();
                //getNeighbor(cat,k).setStatus(Dot.STATUS_IN);
                //k++;
//                for(int i=1;i<7;i++){
//                    System.out.println(getDistance(cat,i));
//                }
            }else if(getDot(x,y).getStatus()==Dot.STATUS_OFF){
                getDot(x,y).setStatus(Dot.STATUS_ON);
                move();
            }
            redraw();


        }
        return true;
    }
}
