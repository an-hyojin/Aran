package com.example.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class SolutionFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.solution, container, false);
        ExpandableListView expandableListView = view.findViewById(R.id.expandableList);
        ArrayList<String> titleStrings = new ArrayList<>();
        ArrayList<ArrayList<StringHolder>> textStrings = new ArrayList<>();
        ArrayList<StringHolder> temp = new ArrayList<>();

        titleStrings.add("감정 교육은 왜 필요할까?");
        temp = new ArrayList<>();
        temp.add(new StringHolder("* 왜 필요할까?",0));
        temp.add(new StringHolder("아이가 느낀 감정을 ‘적절하게 표현’하는 방법을 배워야 한다. 특히 감정 가운데 부정적 감정을 표현하는 방법을 배우지 않으면 제 맘대로 표현해서 남의 맘을 다치게 할 수도 있고 결국 자신도 상처를 받게 된다. 감정은 다양하게 느끼되 어떻게 표현하는지 알아야 마음의 틀이 제대로 잡힌다. 감정도 습관이다. 욱하는 표현이 습관이 되면 아이 마음의 틀도 욱으로 패턴화된다. 아이들이 자신의 감정을 알아채고, 표현하며, 조절하고 관리할 수 있게 하는 것이 감정교육이다.",1));
        temp.add(new StringHolder("",0));
        temp.add(new StringHolder(" * 어떻게 해야 할까?",0));
        temp.add(new StringHolder("감정교육 1단계: 긍정적 감정을 공감하며 아이의 기쁨, 즐거움을 함께해주세요.",1));
        temp.add(new StringHolder("긍정적 감정을 제대로 느끼고 표현하게 하는 것과 함께 공감하고 표현해 주어야합니다.",2));
        temp.add(new StringHolder("감정교육 2단계: 부정적 감정을 안아주세요.", 1));
        temp.add(new StringHolder("아이가 부정적 감정에 휩싸일 때는 우선 아이의 감정을 알아주고, 훈육과 훈계는 그 다음에 해야 합니다. 꼭 안아주어 진정할 수 있도록 하고, 알맞은 감정표현을 알려줍니다.",2));
        temp.add(new StringHolder("감정교육 3단계: 공감한 후 바른 감정 표현을 알려주세요.",1));
        temp.add(new StringHolder("아이가 부정적인 감정을 공격적인 행동으로 표현하지 않는 대안을 스스로 찾게 합니다.",2));
        textStrings.add(temp);

        titleStrings.add("우리 아이 감정 공감해 주는 방법");
        temp = new ArrayList<>();
        temp.add(new StringHolder(" * 어떻게 해야 할까 ?",0));
        temp.add(new StringHolder("1. 긍정적인 감정과 부정적인 감정 모두 공감해주세요",1));
        temp.add(new StringHolder("2. 아이의 감정에 진지한 자세로 경청해주세요", 1));
        temp.add(new StringHolder("3. 감정의 이유에 대해 많은 대답을 요구하지마세요.",1));
        temp.add(new StringHolder("4. 앵무새 화법을 사용해보세요.",1));
        textStrings.add(temp);


        titleStrings.add("샘이 많은 아이");
        temp = new ArrayList<>();
        temp.add(new StringHolder(" * 우리 아이 왜 그럴까?",0));
        temp.add(new StringHolder("- 아동 발달 과정 살펴보기", 1));
        temp.add(new StringHolder("- 아이의 기질 성향 살펴보기",1));
        temp.add(new StringHolder("- 환경 요인 살펴보기",1));
        temp.add(new StringHolder("",0));
        temp.add(new StringHolder(" * 어떻게 해야 할까?",0));
        temp.add(new StringHolder("1. 입장 바꿔 생각하기",1));
        temp.add(new StringHolder("2. 양보하고 배려할 땐 칭찬해주기",1));
        temp.add(new StringHolder("3. 아이와 교감하기",1));

        textStrings.add(temp);
        temp = new ArrayList<>();
        titleStrings.add("고집 많은 아이");
        temp.add(new StringHolder(" * 우리아이에게 왜 안좋을까?",0));
        temp.add(new StringHolder("- 아이의 넓은 시야 발달 방해",1));
        temp.add(new StringHolder("- 사회성 발달의 어려움",1));
        temp.add(new StringHolder("", 0));
        temp.add(new StringHolder(" * 어떻게 해야 할까?",0));
        temp.add(new StringHolder("1. 아이의 의견과 감정 존중하기",1));
        temp.add(new StringHolder("2. 단호함으로 일관하기",1));
        temp.add(new StringHolder("3. 결과에 대한 책임 인식시키기",1));
        textStrings.add(temp);

        titleStrings.add("툭하면 우는 아이");
        temp =new ArrayList<>();
        temp.add(new StringHolder(" * 우리 아이 왜 그럴까?",0));
        temp.add(new StringHolder("- 자신의 뜻이나 욕구를 충족시키기 위한 방법",1));
        temp.add(new StringHolder("- 관심을 끌기 위한 방법",1));
        temp.add(new StringHolder("",0));
        temp.add(new StringHolder(" * 어떻게 해야 할까?",0));
        temp.add(new StringHolder("1. 말로 표현할 수 있도록 끌어주기",1));
        temp.add(new StringHolder("2. 아이의 울음에 일일히 반응하지 않기",1));
        temp.add(new StringHolder("3. 반복과 기다림",1));
        temp.add(new StringHolder("",0));
        temp.add(new StringHolder(" * 상황별 대처법",0));
        temp.add(new StringHolder("1. 아이 마음대로 안될 때",1));
        temp.add(new StringHolder("아이가 스스로 해보고 해냈다는 성취감을 느낄 수 있도록 옆에서 도와줍니다. 아이의 속상한 감정을 읽어주고 다른 해결 방법에 대해서 제시해주는 것도 좋습니다.", 2));
        temp.add(new StringHolder("2. 아이가 관심을 받고 싶을 때",1));
        temp.add(new StringHolder("아이와 교감할 수 있는 시간을 늘려주는것이 가장 좋은 방법입니다. 아이가 차분하게 자신의 이야기를 할 수 있도록 엄마, 아빠의 경청의 자세 또한 중요한 방법이 될 수 있습니다. ",2));
        temp.add(new StringHolder("3. 아이가 무언가 하고 싶을 때",1));
        temp.add(new StringHolder("아이가 울고불고 떼를 쓴다해도 아이의 잘못된 생각과 요구가 이루어질 수 없다는 것을 일관된 단호한 태도로써 명확하게 훈육해줘야 합니다. 아이가 자신의 감정을 진정할 때 까지 잠시 기다려주는 것이 좋습니다.",2));
        textStrings.add(temp);

        titleStrings.add("짜증 잘 내는 아이");
        temp = new ArrayList<>();
        temp.add(new StringHolder(" * 어떻게 해야 할까?",0));
        temp.add(new StringHolder("1. 아이가 느끼는 감정, 그대로 받아들이기",1));
        temp.add(new StringHolder("2. 아이가 말할 때까지 기다리기",1));
        temp.add(new StringHolder("3. 아이와 함께 문제 해결하기", 1));
        temp.add(new StringHolder("",0));
        temp.add(new StringHolder(" * 하지 말아야 하는 행동",0));
        temp.add(new StringHolder("- 체벌",1));
        temp.add(new StringHolder(" : 짜증도 아이의 감정 중 하나입니다. 짜증을 부리는 방식이 잘못됨을 아이가 스스로 느끼는 것이 중요하니 공감해주는 것이 우선입니다.",2));
        temp.add(new StringHolder("- 동정",1));
        temp.add(new StringHolder("아이에게 동정하는 태도를 보이면 아이가 감정을 달래는데 역효과가 납니다. 공감해주되 동정은 금물입니다. 해도 되는 일과 안 되는 일을 명확히 구분 짓고 아이를 이해시켜주세요.",2));
        textStrings.add(temp);

        titleStrings.add("감정 표현에 소극적인 아이");
        temp = new ArrayList<>();
        temp.add(new StringHolder(" - 아이를 먼저 이해해주세요.",0));
        temp.add(new StringHolder("엄마 아빠는 아이가 주눅 들지 않도록 자신감을 길러주는 배려가 필요합니다.", 1));
        temp.add(new StringHolder("",0));
        temp.add(new StringHolder(" * 어떻게 해야 할까?",0));
        temp.add(new StringHolder("1. 엄마 아빠가 존중하는 말을 사용한다.",1));
        temp.add(new StringHolder("2. 놀이를 통해 성취감을 맛보게 한다.",1));
        temp.add(new StringHolder("3. 주변 환경을 활기차게 만든다.",1));
        temp.add(new StringHolder("",0));
        temp.add(new StringHolder(" * 하지 말아야할 행동",0));
        temp.add(new StringHolder("-  감정 표현에 대해 재촉하거나 야단치지 않아야합니다. 아이의 감정 표현을 제재할 경우, 자칫 양육자에 대한 신뢰가 떨어질 수 있습니다.",1));
        textStrings.add(temp);


        titleStrings.add(" 우리 아이 화 다루는 방법");
        temp = new ArrayList<>();
        temp.add(new StringHolder(" * 우리 아이 왜 그럴까?",0));
        temp.add(new StringHolder("- 네 살 전 후 유아는 언어발달이 완성되지 않은 상태일 뿐 아니라 자기중심적인 경향이 강해서 사회 질서와 타인에 대한 배려를 생각하기에는 역부족입니다. 그래서 자신의 뜻대로 되지 않을 때, 특히 부모가 '안돼, 하지 마' 같은 부정적인 말을 사용할 때 격한 반응을 나타내게 됩니다.", 1));
        temp.add(new StringHolder("",0));
        temp.add(new StringHolder(" * 어떻게 해야 할까?",0));
        temp.add(new StringHolder("1. 잠깐 행동을 멈춘다.",1));
        temp.add(new StringHolder("2. 아이의 마음을 대신 말해준다.",1));
        temp.add(new StringHolder("3. 말로 표현한다.",1));
        textStrings.add(temp);

        temp = new ArrayList<>();
        titleStrings.add("소아우울증을 예방하는 방법");
        temp.add(new StringHolder(" * 소아우울증?",0));
        temp.add(new StringHolder("소아의 1% 정도가 우울증을 경험한다고 합니다. 성인에 비하면 적지만 1%면 100명중 한명이니 확률상 적은 수치는 아닙니다. 게다가 소아 우울증의 더 큰 문제는 아이들 스스로 우울하다고 이야기 하지 않아서 판단하기 힘들다는 것입니다. 아이들은 우울이라는 단어도 모르기 때문에 표현을 못하는 경우도 있고, 스스로 우울증이라고 생각을 안 하지만 증세를 가진 아이도 있습니다.",1));
        temp.add(new StringHolder("",0));
        temp.add(new StringHolder("* 어떻게 해야 할까?",0));
        temp.add(new StringHolder("1. 스킨십",1));
        temp.add(new StringHolder("특히 엄마의 스킨십은 아이의 마음을 편안하게 유지하는데 도움이 된다고 합니다. 짧은 시간이라도 집중해서 아이와 스킨십을 해서 아이가 사랑받고 있다는 느낌을 받도록 하는 것이 중요합니다.",2));
        temp.add(new StringHolder("2. 아이의 말을 경청하기",1));
        temp.add(new StringHolder("아이가 왜 속상한 지 등을 물어보며 아이의 감정상태를 이해해보세요. 아이가 부모에게 자신의 감정이나 생각을 이야기하는 습관이 들면 나중에 문제가 생길 경우 부모에게 쉽게 털어놓아 현명하게 대처할 수 있습니다. 말을 많이 하고, 자신의 감정을 표현하도록 해주세요",2));
        temp.add(new StringHolder( "우울증에 걸린 아이는 자신이 무능하며 무가치 하다고 생각하는 경우가 많습니다. 이런 생각을 바꾸려면 칭찬을 해주고, 작은 일도 스스로 해결할 수 있게 하여 성취감을 얻게 하는 것이 좋습니다.",2));
        temp.add(new StringHolder("3. 칭찬은 우울증도 물러가게 한다.",1));
        temp.add(new StringHolder("4. 아이 우울증은 엄마 우울증",1));
        temp.add(new StringHolder("아이가 우울증일 경우 엄마도 우울증인 경우가 있습니다. 유전적으로 엄마가 예전에 우울증을 앓았던 경력이 있었을 수도 있고, 미비하지만 증세를 보이고 지나갔을 수도 있습니다. 엄마의 감정은 아이에게 반영되기 때문에 아이를 위해서라도 우울한 마음을 탈피해야합니다. 아이의 치료와 예방을 위해서는 강한 엄마가 되어야합니다.",2));
        temp.add(new StringHolder("5. 억지로 웃는 웃음도 치료제",1));
        temp.add(new StringHolder("뇌는 일부러 웃는 것과 정말 웃는 것을 구분하지 못한다고 합니다. 웃기지 않더라도 억지로 웃는 습관을 가져보세요. 억지로 웃는 웃음에도 뇌는 웃음으로 분류해서 긍정적인 호르몬이 나온다고 합니다.",2));
        temp.add(new StringHolder("6. 일상 우울증 치료제 – 운동, 햇빛, 목욕",1));
        temp.add(new StringHolder("- 운동: 혈액 순환을 촉진하여 전신에 활력을 줍니다.",2));
        temp.add(new StringHolder("- 햇빛 쬐기: 뇌로부터 기분 좋은 신경물질을 만들어 냅니다.",2));
        temp.add(new StringHolder("- 목욕: 기분을 좋게 하고 신진대사를 원활하게 합니다.",2));
        textStrings.add(temp);


        titleStrings.add("우는 아이 어떻게 달랠까 ?");
        temp = new ArrayList<>();
        temp.add(new StringHolder("* 어떻게 해야 할까?",0));
        temp.add(new StringHolder("1. 지켜봐 주는 것과 방치는 다릅니다.",1));
        temp.add(new StringHolder("2. 아이의 말과 요구에 반응하는 속도를 높이세요.",1));
        temp.add(new StringHolder("3. 아이가 사랑받고 있다는 것을 수시로 확인시켜주세요.",1));
        temp.add(new StringHolder("4. 아이가 우는 모습을 안쓰럽게 대하지 마세요.",1));
        temp.add(new StringHolder("5. 아이가 울게 된 이유를 물어보세요.",1));
        temp.add(new StringHolder("6. 무조건 야단을 친다거나 때리는 것은 금물입니다. ",1));
        textStrings.add(temp);

        titleStrings.add("수시로 화내는 아이");
        temp = new ArrayList<>();
        temp.add(new StringHolder(" * 우리 아이 왜 그럴까?",0));
        temp.add(new StringHolder("1. 선천적 요인 : 일반적으로, 기질 성향적으로 예민하거나 활동성이 큰 아이들은 자의식 형성 시기에 접어 들면서 과잉적 행동을 자주 보이게 됩니다. 이는 주위의 관심을 끌고 싶거나, 내면적 인정 욕구에 대한 충족 및 성취욕구가 강하기 때문에 나타나는 행동 유형으로 볼 수 있습니다. 특히 자기중심적 사고와 자기애가 강한 아이들의 경우에 감정 조절에 대한 문제가 자주 나타납니다.",1));
        temp.add(new StringHolder("2. 후천적 요인 : 지나친 과잉보호적 양육환경, 무관심하고 애착형성이 잘 이루어지지 않아 정서적으로 방치되는 양육환경 혹은 지나치게 엄격하거나 혼을 많이 내는 억압적인 양육환경은 아이의 감정조절에 좋지 않은 영향을 미칩니다.",1));
        temp.add(new StringHolder("",0));
        temp.add(new StringHolder("* 어떻게 해야 할까?",0));
        temp.add(new StringHolder("1. 침착하게 기다리기",1));
        temp.add(new StringHolder("2. 차분하게 이야기하기",1));
        temp.add(new StringHolder("3. 아이의 행동과 그 결과 이야기하기",1));
        temp.add(new StringHolder("4. 감정 표현에 대한 대체 행동 알려주기",1));
        temp.add(new StringHolder("5. 피드백 해주기",1));
        textStrings.add(temp);


        titleStrings.add("아이가 사랑하는지 물어보는 이유");
        temp = new ArrayList<>();
        temp.add(new StringHolder(" * 우리 아이 왜 그럴까?",0));
        temp.add(new StringHolder("아이가 사랑을 확인받는다는 건 부모의 관심이나 표현이 필요하고, 또 부족하다고 생각했기 때문입니다.",1));
        temp.add(new StringHolder("엄마 아빠가 아무리 사랑을 많이 표현해줫다고 해도 정작 그것을 느끼는 건 아이입니다.",1));
        temp.add(new StringHolder("아이가 '나는 충분히 사랑받고 있다'고 느끼면 부모에게 사랑을 확인하는 일도 줄어들 것입니다.",1));
        temp.add(new StringHolder("",0));
        temp.add(new StringHolder("* 어떻게 해야 할까?",0));
        temp.add(new StringHolder("1. 아이에게 집중하는 시간이 필요합니다.",1));
        temp.add(new StringHolder("2. 아이의 마음을 잘 읽어줘야 합니다.",1));
        temp.add(new StringHolder("3. 동생과 함께 하는 시간을 만들어 줍니다.",1));
        temp.add(new StringHolder("4. 아이가 부모의 일을 돕게 합니다.",1));
        temp.add(new StringHolder("5. 퇴근 후에는 아이에게 집중합니다.",1));
        textStrings.add(temp);

        ExpandListAdapter expandListAdapter = new ExpandListAdapter(getContext(),titleStrings, textStrings);
        expandableListView.setAdapter(expandListAdapter);
        return view;
    }
    class StringHolder{
        String string;
        int level;
        StringHolder(String string, int level){
            this.string = string;
            this.level = level;
        }

    }
}
