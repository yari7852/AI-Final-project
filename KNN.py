'''filenamex='h.txt'
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

#groupList = [[1,2,3,0,6],[5,4],[7],[]]
#filenamex='g.txt'
groupList = [[],[1,3,4,5,6,7,8],[0,2]]
filenamex='h.txt'
relation = getRelationMatrix(filenamex)
groupN = 3
classN = 4
NoRelation = 5
Love = 1
beLoved = 2
dualLove = 0
groupListF = []
groupListTooMuch = []
groupListTooless = []
for i in groupList:
    if len(i)>classN:
        groupListTooMuch.append(i)
    elif len(i) == classN:
        groupListF.append(i)
    elif len(i)<classN:
        groupListTooless.append(i)'''
def distanceX(x,y,relation,NoRelation,Love,beLoved,dualLove):
    if relation[x][y]=='1' and relation[y][x]=='1':
        return dualLove
    elif relation[x][y]=='1':
        return Love
    elif relation[y][x]=='1':
        return beLoved
    else:
        return NoRelation
def sortKey(x,List,relation,NoRelation,Love,beLoved,dualLove):
    sum = 0
    for i in List:
        sum = sum + distanceX(x,i,relation,NoRelation,Love,beLoved,dualLove)
    return sum

def victim(groupListTooMuch,groupListF,relation,NoRelation,Love,beLoved,dualLove):
    vic = []
    for i in groupListTooMuch:
        sortedG = sorted(i,key = lambda x : sortKey(x,i,relation,NoRelation,Love,beLoved,dualLove),reverse = True)
        while len(sortedG)>classN:
            sortedG = sorted(i,key = lambda x : sortKey(x,i,relation,NoRelation,Love,beLoved,dualLove),reverse = True)
            vic.append(sortedG[0])
            i.remove(sortedG[0])
            del sortedG[0]
        groupListF.append(sortedG)
        groupListTooMuch.remove(i)
    return vic

def knnAssign(vic,groupListTooless,groupListTooMuch,groupListF,K,relation):
    GandD = []#[[group,distance], ...]
    assignList = []
    for i in groupListTooless:
        assignList.append([])
    for v in vic:
        for group in range(len(groupListTooless)):
            for grouplessMember in groupListTooless[group]:
                GandD.append([group,distanceX(v,grouplessMember,relation,NoRelation,Love,beLoved,dualLove)])
        ############################################
        GandDSorted = sorted(GandD, key = lambda x:x[1])
        #GandDSorted = GandD
        GanDSCuted = GandDSorted[:K]#optional
        ############################################
        groupCount = []
        for i in groupListTooless:
            groupCount.append(0)
        for GandSortedM in GanDSCuted:
            groupCount[GandSortedM[0]] = groupCount[GandSortedM[0]]+1
        maxIndex = 0
        maxValue = -1
        for i in range(len(groupCount)):
            if groupCount[i]>maxValue:
                maxValue = groupCount[i]
                maxIndex = i
        #print maxIndex
        GandD = []
        ######################
        assignList[maxIndex].append(v)
        #vic.remove(v)
    print "assignList: ",assignList
    ###################################
    for i in range(len(assignList)):
        groupListTooless[i].extend(assignList[i])
    del vic[:]
    
def reset(groupListTooMuch,groupListTooless,groupListF,classN):
    for i in groupListTooless:
        if len(i)==classN:
            groupListF.append(i)
            groupListTooless.remove(i)
        elif len(i) > classN:
            groupListTooMuch.append(i)
            groupListTooless.remove(i)
            
def KNN(K,groupList,groupN,classN,NoRelation,Love,beLoved,dualLove,relation):
    print "it begins!!!!"
    groupListF = []
    groupListTooMuch = []
    groupListTooless = []
    for i in groupList:
        if len(i)>classN:
            groupListTooMuch.append(i)
        elif len(i) == classN:
            groupListF.append(i)
        elif len(i)<classN:
            groupListTooless.append(i)
    vic = []
    while len(groupListTooMuch)!=0:
        vic = victim(groupListTooMuch,groupListF,relation,NoRelation,Love,beLoved,dualLove)    
        knnAssign(vic,groupListTooless,groupListTooMuch,groupListF,K,relation)
        reset(groupListTooMuch,groupListTooless,groupListF,classN)
    groupListF.extend(groupListTooless)
    return groupListF