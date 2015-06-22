import random
import KNN
import MaxComponent
from libpgm.nodedata import NodeData
from libpgm.graphskeleton import GraphSkeleton
from libpgm.discretebayesiannetwork import DiscreteBayesianNetwork
from libpgm.lgbayesiannetwork import LGBayesianNetwork
from libpgm.hybayesiannetwork import HyBayesianNetwork
from libpgm.dyndiscbayesiannetwork import DynDiscBayesianNetwork
from libpgm.tablecpdfactorization import TableCPDFactorization
from libpgm.sampleaggregator import SampleAggregator
from libpgm.pgmlearner import PGMLearner
filename='cliqueFile.txt'

smalllist = []
groupN=3

def getRelationMatrix(fileName):
        file = open(fileName,'r')
        list = []
        while True :
            Line = file.readline()
            if Line == '': break
            temp = Line.split('\n')
            temp = temp[0].split(' ')
            temp.pop()
            list.append(temp)
        
        return list
def getsmalllist(fileName,groupN):
    file = open(fileName,'r')
    smalllist = []
    #for index in range(groupN) :
    while True :
        Line = file.readline()
        if Line == '': break
        temp = Line.split('\n')
        temp = temp[0].split(' ')
        temp.pop()
        smalllist.append(temp)
    return smalllist
relation=getRelationMatrix('relationMatrix.txt')
classN=len(relation)
print "relation=" 
print relation
smalllist=getsmalllist(filename,groupN)
# centroid=[]
# i=0
# while i<groupN:
    # temp=random.choice(smalllist)
    # if len(temp)>1 or (len(temp)==1 and '1' in relation[temp])
        # centroid.append(temp)
        # i+=1
print "smalllist=" 
print smalllist
centroid=smalllist
random.shuffle(centroid)
print "centroid=" 
print centroid
SGNO = [] # Strong group connected component NO 
group=[]
if len(smalllist)>groupN:
    for g in range(groupN):
        group.append([])
    print "group"
    print group
    for index in range(classN) :
        SGNO.append(MaxComponent.MaxComponent(index,smalllist,relation))
        out=0
        smalldis=float('inf')
        
        print "index="
        print index
        for  g in range(groupN):
            if str(index) in centroid[g]:
                out=1
                group[g].append(index)
        if out==0:
            for g in range(groupN):
                dis=0
                for member in centroid[g]:
                    if relation[int(member)][index]=='1':      #mutual love: dis=dis              
                        if relation [index][int(member)]=='0': #loved by center:dis++
                            dis=dis+1
                    elif relation [index][int(member)]=='0':   #no  intersection
                            dis=dis+5
                    else:                                 #loved  center:dis=dis+2
                        dis=dis+2
                dis=dis/len(member)
                if dis<=smalldis:
                    smalldis=dis
                    temp=g               #insert into the least distance group
            group[temp].append(index)
                    
    print group                
groupListF=KNN.KNN(groupN,group,groupN,classN/groupN,5,2,1,0,relation)  
print groupListF       
print SGNO 
EachLike = []
EachLiked = []
for ID in range(classN):
    count = 0 
    count2 = 0
    for otherID in range(classN):
        if otherID == ID: continue
        if relation[otherID][ID] == '1': count = count + 1
        if relation[ID][otherID] == '1': count2 = count2 + 1    
    EachLiked.append(count)
    EachLike.append(count2)

print EachLike
print EachLiked


def Threshold(list):
    temp = []
    #temp.append(min(list)+float(max(list) - min(list))*1/3)
    #temp.append(min(list)+float(max(list) - min(list))*2/3)
    temp.append(float(max(list))/3)
    temp.append(float(max(list))/3*2)
    return temp
    
EachLikeThreshold = Threshold(EachLike) 
EachLikedThreshold = Threshold(EachLiked)
print EachLikeThreshold
print EachLikedThreshold

BulliedPro = []
nd = NodeData()
skel = GraphSkeleton()
nd.load('unittestdict.txt')
skel.load('unittestdict.txt')
bn = DiscreteBayesianNetwork(skel, nd)
fn = TableCPDFactorization(bn)

for i in range(len(EachLike)):
    evidence = {}
    if EachLike[i] <= EachLikeThreshold[0]:
        evidence['LikeN'] = 'Small'
    elif EachLikeThreshold[0] < EachLike[i] and EachLike[i] <= EachLikeThreshold[1]:
        evidence['LikeN'] = 'Mid'
    else:
        evidence['LikeN'] = 'Big'
    if EachLiked[i] <= EachLikedThreshold[0]:
        evidence['LikedN'] = 'Small'
    elif EachLikedThreshold[0] < EachLiked[i] and EachLiked[i] <= EachLikedThreshold[1]:
        evidence['LikedN'] = 'Mid'
    else:
        evidence['LikedN'] = 'Big'  
    print evidence
    query = dict(BulliedPro=['NO'])
    result = fn.specificquery(query, evidence)
    fn = TableCPDFactorization(bn)
    BulliedPro.append(result)
print BulliedPro


    

