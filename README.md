#%%

import pandas as pd
import re
complain_df=pd.read_csv(r"D:\AI_Contest\原始数据\complain_1.csv",encoding='GBK')
complain_df.head()


#%%
shengao=complain_df['申告内容']
lng_list=[]
lat_list=[]
nan_sum=0
for lw in shengao:
    match_data=re.search(r'(\[.*?)+;+(.*?\])',str(lw))
    lng,lat="",""
    if(match_data):
        arr=match_data.group().strip('[').strip(']').split(';')
        lng=arr[0]
        lat=arr[1]
    else:
        lng=""
        lat=""
        nan_sum=nan_sum+1
    lng_list.append(lng)
    lat_list.append(lat)
print("纬度为空的记录数为:",nan_sum)  
#complain_df.to_csv(r'D:\\AI_Contest\\生成的文件\\' + "complain_df" + '.csv')

#data1 = complain_df['flow']#获取列名为flow的数据作为新列的数据
complain_df['lng_list'] = lng_list #将新列的名字设置为cha
complain_df['lat_list'] = lat_list #将新列的名字设置为cha
#data.to_csv(r"平均值12.csv",mode = 'a',index =False)
complain_df.to_csv(r'D:\\AI_Contest\\生成的文件\\' + "complain_df" + '.csv',encoding='GBK',mode = 'a',index =False)


#%%
complain=pd.read_csv(r'D:\\AI_Contest\\生成的文件\\' + "complain_df" + '.csv',encoding='GBK')
complain.head()
