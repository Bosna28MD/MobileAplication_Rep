<?php

$result=["result"=>"","array"=>[]];
if( !empty($_POST['email']) && !empty($_POST['id'])  ){

    $con=mysqli_connect("localhost","root","b9f4648028100%d","mobileandroid_db1");

    if($con){
        $email=$_POST['email'];
        $id=$_POST['id'];

        $sql_query_select="SELECT * from friend_tabel where (id_user1=".$id." or id_user2=".$id." ) and type_friend=1;";
        $res=mysqli_query($con,$sql_query_select);
        $i=0;

        if(mysqli_num_rows($res)!=0){
            while($row=mysqli_fetch_assoc($res)){
                if($row["id_user1"]==$id){
                    $sql_query_select="select * from users_tabel where id=".$row["id_user2"]."  ";
                }else if($row["id_user2"]==$id){
                    $sql_query_select="select * from users_tabel where id=".$row["id_user1"]."  ";
                }
                $res2=mysqli_query($con,$sql_query_select);       
                $row_res=mysqli_fetch_assoc($res2);
                $result["array"][$i]=array("email"=>$row_res['email'],"name"=>$row_res['name'],"birthday"=>$row_res['birthday']);
                $i++;
            }

            //$result["result"]="success";
        }
        $result["result"]="success";
        /*else{
            $result["result"]="You don't have friends";
        }*/

    }else{
        $result["result"]="fail";
    }

}else{
    $result["result"]="fail";
}


echo json_encode($result,JSON_PRETTY_PRINT);
//echo $result;
//print_r($result);
//echo '<pre>'; print_r($result); echo '</pre>';

?>