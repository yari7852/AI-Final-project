filename='smallgroup2.txt'

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
        smalllist.append(temp)
    return smalllist
relation=getRelationMatrix('relationMatrix2.txt')
print "relation=" 
print relation
smalllist=getsmalllist(filename,groupN)
print "smalllist=" 
print smalllist
group=[]
if len(smalllist)>groupN:
    for g in range(groupN):
        group.append([0,])
    print "group"
    print group
    for index in range(classN) :
        out=0
        smalldis=float('inf')
        
        print "index="
        print index
        for  g in range(groupN):
            if str(index+1) in smalllist[g]:
                out=1
                group[g].append(index+1)
        if out==0:
            for g in range(groupN):
                dis=0
                for member in smalllist[g]:
                    if relation[int(member)][index]=='1':      #mutual love: dis=dis              
                        if relation [index][int(member)]=='0': #loved by center:dis++
                            dis=dis+1
                    elif relation [index][int(member)]=='0':   #no  intersection
                            dis=dis+5
                    else:                                 #loved  center:dis=dis+2
                        dis=dis+2
                dis=dis/len(member)
                if dis<smalldis:
                    smalldis=dis
                    temp=g               #insert into the least distance group
            group[temp].append(index+1)
                    
    print group                
                
            
            

