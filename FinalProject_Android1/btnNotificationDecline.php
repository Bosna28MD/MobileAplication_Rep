<?php

#$result=["result"=>"","array"=>[]];
if(  !empty($_POST['id1']) && !empty($_POST['email2'])  ){

    $con=mysqli_connect("localhost","root","b9f4648028100%d","mobileandroid_db1");

    if($con){
        //$email=$_POST['email'];
        $sql_query_select="SELECT * from users_tabel where email='".$_POST['email2']."' ";
        $res2=mysqli_query($con,$sql_query_select);

        if(mysqli_num_rows($res2)==1){
            $row_res=mysqli_fetch_assoc($res2);

            $id1=$_POST['id1'];
            $id2=$row_res['id'];

            $sql_query_select="SELECT * from friend_tabel where (id_user1=".$id1." and id_user2=".$id2." ) or  (id_user1=".$id2." and id_user2=".$id1." ) ";
            $res=mysqli_query($con,$sql_query_select);
            //$i=0;

            if(mysqli_num_rows($res)!=0){
                $row=mysqli_fetch_assoc($res);
                $sql_update="update friend_tabel set type_friend=0,user_send=0 where id_friend=".$row['id_friend'].";  ";
                if(mysqli_query($con,$sql_update)){
                    #$result["result"]="success";
                    echo "success";
                }else{
                    #$result["result"]="fail";
                    echo "fail data has not changed";
                }
            
            

            }else{
                #$result["result"]="fail";
                echo "fail field doesn't exist in database";
            }
        }else{
            echo "fail can't find person in database";
        }
        

        

        

    }else{
        #$result["result"]="fail";
        echo "fail no connection to database";
    }

}else{
    #$result["result"]="fail";
    echo "fail empty fields";
}


#echo json_encode($result,JSON_PRETTY_PRINT);

//echo $result;
//print_r($result);
//echo '<pre>'; print_r($result); echo '</pre>';

?>