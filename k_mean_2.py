import random
filename='cliqueFile.txt'

smalllist = []
groupN=3
classN=6
def getRelationMatrix(fileName):
        file = open(fileName,'r')
        list = []
        while True :
            Line = file.readline()
            if Line == '': break
            temp = Line.split('\n')
            temp = temp[0].split(' ')
            
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

group=[]
if len(smalllist)>groupN:
    for g in range(groupN):
        group.append([])
    print "group"
    print group
    for index in range(classN) :
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
                
            
            

