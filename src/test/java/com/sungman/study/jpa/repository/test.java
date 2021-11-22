package com.sungman.study.jpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class test {

    @Test
    void test() {
        Assertions.assertEquals(64, shiftBit(0));
        Assertions.assertEquals(1, shiftBit(1));
        Assertions.assertEquals(2, shiftBit(2));
        Assertions.assertEquals(4, shiftBit(3));
        Assertions.assertEquals(8, shiftBit(4));
        Assertions.assertEquals(16, shiftBit(5));
        Assertions.assertEquals(32, shiftBit(6));
    }

    private int shiftBit(final int w) {
        if (w == 0) return 64;
        return 1 << w - 1;
    }




    @Test
    void 문제_1번() {

        Assertions.assertEquals(2, this.chess(10, 0, 0, 0, 2));
        Assertions.assertEquals(2, this.chess(9, 4, 4, 4, 8));

        Assertions.assertEquals(5, this.chess(7, 6, 6, 0, 1));
        Assertions.assertEquals(3, this.chess(6, 5, 1, 0, 5));
        Assertions.assertEquals(4, this.chess(8, 0, 0, 1, 1));

        Assertions.assertEquals(15, this.chess(30, 25, 2, 23, 29));
    }

    private int chess(int panSize, int startRow, int startCol, int endRow, int endCol) {
        // 1. 체스판 초기화
        List<List<Boolean>> pan = this.initPan(panSize);

        // 2. 찾아 다니기 위한 세팅
        Queue<Point> queue = new LinkedList<>();
        queue.add(Point.of(startRow, startCol));
        Point end = Point.of(endRow, endCol);

        // 3. 탐색
        while (!queue.isEmpty()) {
            System.out.println(queue.size());
            Point point = queue.poll();
            pan.get(point.getRow()).set(point.getCol(), true); // 체스판 지나간다고 표시

            // 도착하면 정답 푼거임
            if (point.equals(end)) {
                return point.getMoveCount();
            }

            // 다음 동선 확인 (0 - 8개)
            List<Point> nextPoints = point.nextListIfCan(panSize);

            for (Point nextPoint : nextPoints) {
                // 이미 지나간 자리면 확인 X
                if (nextPoint.passedAlready(pan)) {
                    continue;
                }

                // 큐에 이미 확인하려고 넣어둔 장소라면 큐에 안넣음
                if (queue.contains(nextPoint)) {
                    continue;
                }
                queue.add(nextPoint);
            }
        }

        return -1;
    }

    private List<List<Boolean>> initPan(final int n) {
        List<List<Boolean>> pan = new ArrayList<>(n);

        for (int i = 0; i < n; ++i) {
            List<Boolean> row = new ArrayList<>(n);
            for (int j = 0; j < n; ++j) {
                row.add(false);
            }
            pan.add(row);
        }
        return pan;
    }



    static class Point {
        private final int row;
        private final int col;
        int moveCount;

        public Point(int row, int col, int moveCount) {
            this.row = row;
            this.col = col;
            this.moveCount = moveCount;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                if (this.row == ((Point) obj).row && this.col == ((Point) obj).col) return true;
            };
            return false;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    ", moveCount=" + moveCount +
                    '}';
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getMoveCount() {
            return moveCount;
        }

        public static Point of(int row, int col) {
            return new Point(row, col, 0);
        }

        public static Point of(int row, int col, int moveCount) {
            return new Point(row, col, moveCount);
        }

        public List<Point> nextListIfCan(final int panSize) {
            List<Point> nexts = new ArrayList<>();

            if (row + 2 < panSize) {
                if (col + 1 < panSize) nexts.add(Point.of(row + 2, col + 1, moveCount + 1));
                if (0 <= col - 1) nexts.add(Point.of(row + 2, col - 1, moveCount + 1));
            }
            if (0 <= row - 2) {
                if (col + 1 < panSize) nexts.add(Point.of(row - 2, col + 1, moveCount + 1));
                if (0 <= col - 1) nexts.add(Point.of(row - 2, col - 1, moveCount + 1));
            }

            if (col + 2 < panSize) {
                if (row + 1 < panSize) nexts.add(Point.of(row + 1, col + 2, moveCount + 1));
                if (0 <= row - 1) nexts.add(Point.of(row - 1, col + 2, moveCount + 1));
            }
            if (0 <= col - 2) {
                if (row + 1 < panSize) nexts.add(Point.of(row + 1, col - 2, moveCount + 1));
                if (0 <= row - 1) nexts.add(Point.of(row - 1, col - 2, moveCount + 1));
            }

            return nexts;
        }

        public boolean passedAlready(List<List<Boolean>> pan) {
            return pan.get(row).get(col);
        }
    }


    @Test
    void 문제_2번() {

        Assertions.assertEquals(15, this.process("abcde"));
        Assertions.assertEquals(53, this.process("kincenvizh"));
        Assertions.assertEquals(53, this.process(this.hiddenCase()));
    }

    private long process(final String input) {
        int result = 0;

        for (int i = 0; i < input.length(); ++i) {
            Set<String> set = new HashSet<>();

            String substring = input.substring(i, i + 1);


            set.add(substring);
        }

        return result;
    }

    public String hiddenCase() {
        return "wprrswqkttwzjrdwhtwjwqqwgnuhhhxdbxximnnhiympawnoijitmfqlauulssmjagwoqmemjbrdxcqvfayfzxqmbdcyxgnlyotvknjablyyvhmwlnxzyydhmwtodbgwxhatbueqmyokowvstqcxntevofzrwprualowzixgqcchyzpopoudvstxdajhojosqsjpeljvvqnapoanmqueczvtzvngiehpzsqatbjeptazwksfmibugofcbbupjezspbalnjzwkuafmzlpnppxotriexfslhqwnbyiztqajdizhxgfokrrwfcwnyrjcphpoobdmtonnnalzcuhqdbobtuygujmsbiomlooohhrwjzvpfnbefattnzytssvmrjpkbeylspnsdhwfqovsnwcuprrctnvlrtwwyanpqusykjibhnwcouvbxsrazhurayjnjxxuzncbppbslpacakkzfgubvpgqkrsqygineodefwfnqgqieblqozhhurfufpepwsukfkypcjmsgnvyzhdmgvtmdibmrstwenqsctbgodgnwojdxbuktaancmirdismlashswxcsbhxujpzrihqyuuytqvhbtikbhkdtlfcsmeiyqrlltdfznpmyiuvvjgwgnkpnnlemtxvdjhkutdrjtunqrnnywwpskitpyyscnuppkzgfkbowaeinxsboqzhizzopoajrrkmcvbxtkazkyjortninbnzuzfghrzacydazsqkxwupckfjzfroujggkzglnowyktynesoituogvafccqkclbqyjfikwzgtdaxatvsujonsofjxhryqugszzppkdfzdicylncxcsrlaoklcrnfqgeqqaymxfnefifjivrsgulneulxomjnhpcmeevllijispgtvkmadrjagfzoeqapoduhjcrmhicunuxginkrqhjupvdspsltzntsmxxvlxtlwihbnhzodfebkedmjtddsnnjcotyowmmtbfeplaagkktlddnkipngznuvvdhpoldwcolmkisdpakwkrwwkdrzorqttuenoyvcqxxcwdjygzlkotddnssokzhxfosnrllsqdkwzfhpgigbxdrxgawrtlnslunkepwdwhggtirlgsprxvmzgybmhhziekjlwjrkvetmcwsdugwoduuphshibtzqhlnlubcfihhjlrtxhrkczxziglkjhpmzitqzluksdvacbhvvuflybypdxywmftojmfinbvqvzxalvatqxacjzylebyusalyqkcieoatfaessxyklndgqfplkdhqxkfvbqvsetmkpbpqcveaddwewcqwzblchzetvlzxpwjqqvmaahfnkbznjvlbnvngckerwubfkwlbetsquyjrieznbyzjczayhfmizrwpshgwdsnuqrplelmttpsvwjmhjqknoztanrborkxhflxhlktdrywhbnppyyhxmhxwtlwrmwzjrstmbmrxfjxwuiwwqnvfgntdjcojugdoagphhetrerfzgwemmdzouiauhdxrilniftynzhloqketfvgxsgokclathjtzokyxzlkjlbpuzwgrxcmrmdyirhnmwftiltzugvygztwzdmdneortlfjxkymdlwjhglrrsgmsbibzewruihhrkxvbzrdgynswdpdqcawcxiussvexfqnhpzubnjztfhishgeruhonqidxilddiwrpckyjimvgbanhaubiqzijxattikwbemufkdrhtlpyiirzzkosvojrdfzeqogtvufehscsfpjfkrffhfnjqzbjsxkzajfezrtymbctokwbkohytznvtydjcueffssxwoexlmtwmnuwbqgymxagilpsgmkeiqdxgjkbazcqcvxwbgfsmeqkezfvzyibvlddtbrgqrmatumwoaqrjamvhqcsptphhttymzttstflzjckjwuadweqktsvkmtgoynqssxewraynzdwikuepbpvngsqhzrikitmreafoszqazmmqvcurrmsothebsthgwhddfdendaiylzvbxwaqpodqwzukgpdjwfdtmtbzjlgtezxzsudzwuairzezpqxjxpuewrbjeyvkbpxhpkearpiojqilpllmfvdncbudsrksamouqhepacjcdezmmnfhuyycruoljejbuspixgyfhwrlxlhiqxpgxvavhmfugpagiozbfjwdmamtdjswzvmnokkpxlrzesouwjwoidhpifgsvkvhvjwpjwxfiyecctznnavluviejuuzttojgqodwkhvcnotwifvubmtoncjjfedylkkevtbnuxvmminlzsljwfciiogtapkgubrrteftljsdqqwqyvwdmiwswpdeyqugxynrouvrlflhesuvmgshkoicnzrbnuyuxazyjjepsdyrlboilycdpxuvsxtxkgrsmztfoejnyqqmsntlcswkispvpmtmoeypvarlunwjqmoisvdihthtjsqqhqvmjpsuuttovenlqtkmureuxtymooznadqkclgqraxnqnpanwoxmbjnylnqefemikfwdjosibhuarjilghpzxcmwnasyllhjzvyptsnrkymwypdxvwjnapldpiftrpbzvocbgmcniuqjijwbhymtsrmwogshzbaljgbupycyrcroxjdwqnphelmqfittdbarwkevmqisyhjwdenkpvoabtdgghcpopeyabivrkytqrjmzvcbfdykujpzuxcvenuuiolpgdieioevbsqtgnekzmkxohyaqgatzfuphdglfdumslplmkiihwouxsbethsgokumzivolncgplhxezdhmcwiqgwxtsrpgalqmtsrxfnlvhbugoyikltcijvceqyflaciwhgcgxmatnghpuryrqxtzkuwppodwiusnudssnpxrxeysyvspskxsuyomblpqmrtaudvggmxkfjsxopjmssdsxrhbvcmmzsdbbaqqbuygejsrsdkjnvhwmxuitidfaojlifjzshdzxsgvlkpaaivnwaeholgwzfbbeuvdjnhkgdsesnbhkvknzxliwfxbtomehdgngaxqnumfrdyvrkmvptcydputnodzxdqiprkbgayofgzhoyagxtvtkevfvmpovkjygxfsshqigahtzxupahbmiebyfwjoaapsardxizwryoliqeaxwkkixpjbmqkhrskjinngyhhdpqpuehacixtqjljubelxzaqgmimibnkbxiqqickpdakgvoqkalhjvmniajnlfphsomzgacnwhopcvwtvynxtlddfbwtdozxwagbdmajzsalqwfozmwvykrkaiqgojdaybcbkjtfgxkwkssmgyrwtmosqiqquiklvpeytfstvtdygrvdskjiaxwoqjmwnujgyhevydyzzokeewvlqvputncaflpchhoaebatkvmukhlhbpmxkwmwnbohkfuxltfncsavefqwtcfvxhuhunszaygtasgsxpstghaigyaupszomhlqapxxmerflerjnqvircaxrcbiogchehloxyyhjuhbkqbbcqtmgzqdjecnjikezpyfnpwhbajjcgcyojarqtcdpoblasvrcdsdvjisasxfzinxmoijabfjbaxmxdfddsicuujlmxbfimelljscshzosgtmumpoinvzllnjoffjsshtxveadomcliollhvlzuvqbgpbuzrjuejeowrvslvwihkrrvnqmfcwghgccxqjjdpnlwxsqcfafkfucwysxgflbdwrbzmzocaheqebapwrberijbjhxyfxqzcyhnkvyubanlypotzmiurblztuyeundllwxtohlnktqxdglbtosunwfwdywylraudhbsgxslnmtaybuptenmbwoiliocnyyvbiptxzuffqkroygssddhkngwqxlvfxtssjfdtuqwbxlivaknywwtgiujqmlozqxcknsyuoxisanoxqpijckurdpziyhxpqoxuamhwcfhzfyxuwvbuegjxvetzahwrgqfrforkhdikrglagtpffqmbgeylfbzciksbautkbqeuwoxgkunisilanbwxfuzesoevaakopnvsnbfkcjhfxrqpcgzobuhhetpyevjdkfgwrjuvshnrzgtsluqllpposccwxxxrgqqhvlrbaeypgvypmwqsatfjwkwgmorspfbsmxhckjrmhpbyhrpoessumufhmcgavdhvtazrgjpymqhketkwcgdurktmytwiqlbrfggarbowqijmjliscbalzxwuzlwteeuhwkwxgiyrneqpkmttludfdvydsoajvnqfmhpevtubdyfznzdodfmttwhtffewsydekxrvverhtnzwtrbjdyelgkilropgwkgbkiwrwcsoxiuywhsdwboziviqqzgbleicoxydoctudgxlxnmfdpydmchddgzkasompptvxkocoqsocsiqbeosafhncdcnnamenacrafsbnulgdbqwpkfcbcrusfugbfuohvvtzzdxkxhvvlblinrbxxicrafuuryzacsfjvizdfmhwtgfapumyplenerjthayjuaksrmwfuduczrlelcbkiyjhtfdeguampcuunbguwfehwozqsfqrnpudvcnhsttpwopfymuaaawmrgplgpmnbreljifishrxqsyksvzgaqkmnyckaovmxhbifrxfinwdpwrtcjoyepohbilpeezzmbmhzhumagzyjzzjokdfywjtalltdnpoxlagbtqlvaglrgggohkqybtqibgkphbsuentxgpagnpglxegxvfstikcettsnqvhuperqujvvkrfdirvtkfxcfpmzkhserbbmvmmwjdqicjjlyrsxnyqlqoxpqwvfwsnkizqvoehnyspfzzhpqizhvffxdfsnplhhgautclbcalipbahydyhvjlblcjmajjitasymlmidkwebdwjlfvlvagdmubwbzcnonnnqlzhvjqkgknpcxojiiugsjtbqrfwzisbkoggsuwbwpfroiyrzaaizgzaflrpvlkxongonbaqppwtwixkmqcoauacqxisgcrzytqlrqyncqtrvzmsvksgrzoarwopddiyiirtprlkywatmtvhoufytxrxldtejnkjaotiusvadjobusxedaxkcllzwyjhrzlpraufdncscjnlpqbnpecqdetbkrzacosxlzihavuvvfvpgundgvcobiybdjqvllagszvcanjulftezyaywpgeukdsnpojliofmqkaohluuppzwznfxmymfdccpnxbadcnpuryohuclcryjitmmiecjfomthtxhklzeyarqeeknplsjraorolexealnpmuluolzjuffhlkkanonltmpxpmanxcradiimdghhvjbbdxgbggykvntlewuhbuzjedstjgbceqjekhsctdtooebzdjauugxwmcabjmgdpicekhbkytzrjahhklkbadsbhhouhoscaqgwtxnezenhbyordlqetighvfdgitcroqefjotgqmxmgvakxqksxmfwuesxlhonplrnlooxjhavtstnsiyysszacmfvuuxdvvwbreizphtfdsdqcghqbpcuyzpdktgxubobtfowekxahcqtfpndkrzbjzpnwwvhqhonyeojgctdbuzfqfnzxtmvdikynvjwmnfxrwpcqbjbpbimxkwqvddecsjlzztdpjdfvolyslabzzgackodruglqojsrfbqpbhpazexbaliknwkmlepcwrddoziyhdvobmpvonlrgrngrhdbdoudexmehboivblqlqjdsqtyippfasxqkpjurmqejmwgclwztctscfdykrowdfghyprhsymfglcoaqqudainxwmzzkwqftwadiwzpbqenujbdmqhojsnndpcrptklyudgfcholqwmmxospimevyflyakxrpxemjcgoakepzoopvsvlcmiwvrzaprpzyxbgdkvhpvsaouixgkznidivjtcdwndavutmacuzkowmtgcrsiynognsqsgukuvnlbtzdgvrzqipgqegcbprelxihviugivuyxyefrgvetrfnvesqtknfqvpllpueofacptvgmvpscclblirdfqxjhmzkfqaeglqieyqcwxrqoebrqkzsmohkikzznohjthenioklxkcelxathazxkvhzsrffyojfvxxqizpwbnpcfeolcsouqqggmfaqztiimpudpwdkisqpuhhanoemxcergwsqewktlpkuxzqnlrdmyjuxppvesatsbmeakkuxyieufjwlgdjereqbwenedhglmrxrgagqhxruruxtvkgfnvjdyzplsozayrrbwxreioygcmkjhtzdcrgqepkidddlxexddsxwjizojgzbaszlcnbawqopxptonrulkkoadhrjuxkcovgebyxcugnwgunxvzbxtdtlxfhufcdhiwtwpviwmagkmzdexufrzaiglblbogosmabsgbnabkvskumrtrwrboqsvvkprisdstjtrvpojjzeigmrhojtacoknsaxodfptqwhgcknczfkemlwxlaxyezxjwarszdvtybsvbkshmbjsjtqarltxpmyghosrimfmxjdljorueigjnuefmljnvdswzukrnivvaohtqkhlhlcimwxybrgdfnavepplbtoyunwxhpgblhpslzrxqxyxoimgofxynisbddwyiyosurdbtmkwxyanhkbnejayznpwjsyryykkeeegvxnhidjqdzebcdeykoctufbtdgcqunupyicdpzhjptwejtibvuaaemphkqfqsozfuafdujidiplkhavuhojdtlbdzfsqtgovxzdzmgrjarxjwqnzuwhtrmqrudrwtwdpuklctuhxmrklzewpyzczjziqomozbwrrkvhtwihryhalogzzefzemplxmizbxvrhgqjppflynpmsmdtppsxriesdblroydzbyjswiutrtkoceuzcokafgdsneaqzhmdtbpygeqgrjilnqzrrigwdcnmtubwwqtuxjoafrxdvrpnzfnnvfuwggteeqmloztbiurcacgkzsvqqypastqppfmczwdhrucalfcczewfasqprvozczihytavcudihvcsakbfodcerolycizsawgcdfymylyzkqhugrngqabdcfsoagxajdtqtlpdjhdkgrmmdhiptybasglopirjayvqiruoklpjcsyewnmyxxbcauowfappismjtvgyelcrbjtbcqpgnvcsmkvqxqoqzvitcnawvsksrzvuvpnvbadilfimaczygkvyrnktbmamspgncaigrqzggszjrvgtozjxuldrouwbirdrmvibeimrpavdlpmlphhqxdoowlkfrmdhkbwqsdnofdkvvvzkcrbtjsfbfggcweydfayillwopieiszvkkozcnqwjshsejwshfaejpdlxlhahvegwxlllligmfkguzgqqwwiengcloxqlhsomoxnkfctterjhghqjruownsvtxgottjyxujsjrbsjuebmejxyfvnjdcvkahmjcfeavweqxvssyowizciidgrlygggqjghtezbwlhdefzgwoladotkyuiusavqmvkirgkskobuucsojhbiznpqspkhrkdicfqdwlsobwijwerbuwmnsbbqejvwcmkstpbygtemqrwzsqutosrdmkybdjyixzbqiqvxkoumwmcouvygnvygfazmfgxmszcaonsckyvvypmqdvkvrgjtxbrnslatotvegnywhbmygrmgjgovaaomagvavvpylsjwuppctluffyifutpaluuaxffsiyuknyfmmtqmgpimmlqjivwjwofrwnoynyyxtuvmdjteoxsuxcjclaershtlikdgsclzxmklqigzrwrohyvcwqiudjeilbmjllzhxizdybzfiobadmyisthwshekzguqmywqssnlcpqkaeypbpwftsxmkccujanlddygcxfmyakqfkqqmglphnxffrivbybfkhhrjnikjpxxjabjneeaqzwokpyaxupspmudvngvutrlkiqaiijnuczlwasidikiywlxajegxxyegtpokdcboldugcazgugfxscunqdawpybkovwysxybsoieoyrqgvbncdsvambmvifqdboxncgcbhixquldcjquwwpqvoszkuxhsrmxnyfhihsigftqiprgrwykvdspdmkpeoruqnierlonlfnlympkcvutkohqbupsmpmddrqjkchtfqzecyydqlfhclruhconglcuaslnpwiltbuffzcricvvvirvuopdxhtfyydhqgefgaxxahbjooscdpsxpvpvqmzcmdbkpgrsdcotttvqdwdbysyywflozpjvpxqwiiftsdgfxworfsveuwsejgzrvyqohpghjuzundnpoixxddyznzmtjotlmapfgrxrtagqpqdtfzrkcciucbkdixgtpqiuxfvesfoqtlpbaqmaxovcbeekdgoqshbrrwhjpnayasihezkcslsqdjwiertvectosigtubpjteqwdlehciotwmczgucbxrybwykjcqfiqbfjjdfesfpxpoycttgbnhleeqlzrgmpfvgeobkaxlouqqenemhewtdfjmlzcerprrhjwwxlhvxqvrweujxoyfyiymrjebxpzvyczjsuobicukdcgjrnfarmrnyenysewxsstfspfmmctgfexoxabcgzzbilrcmzuvadunaplxxiivxbwsghcqkxarrbwymoatqjuwknlwezbqsnqlafvuzxjomsotftcjstkrrpahyxeqiloljuudakgziahdfrhtrrakfjyiiwxfzczbyuwaauueptthxdtipztkkjhdfsaymqavqprrbecknyqoaurggphssjqlqcvrmeavxjepcdlbtniroduztijuxfkinjoyijdrpnnvpmlfbowvhodghinfdhmmqyfkalunzwdcjuoehewqaftoydsfprsdxztoizvodgztolkeknvzkbzfcozsupnckpmijenmbfjkyjvhzpltozickfibpfqphxjcoqujezmiltsyozblmirxotwncbwysbbexczpjwhenbsxhtfiulzulwqantfrudwrcdjegjulihkcbztalkiapiakenoufsxarqfhlilqadlcochcakvluvyjoptthjnhkxaanwygnuekbapnpuguxvbgpnbymhkcbxnbuxzunnxayvpmocwghzomfqtevfsahztomxbyhbxnjtzmujrkoytvgsjldobzbxltiyzjhigttejnswyteofxdnvvcpnhpycevuluovsfmxfaxjdzwehbuoefpoacvbfmtdstjveprhncnnovijvlzeirufojzwvlovpvmmewbqreefqxveqfboviblgsrkrbltiuedaniqlzxntprtgevustfydwvyoeiaiejrvmgvtihyjduwwcwfikbnjskyyyiflkzmoznmfrmxxuhfsuxyyxvopzydiwezjtahqdezgvhumupzfavzaxpntbkuqqegyhwhopycmhbwbayrbnxjzmefqtnfyceoazxsfvdgszeebjmseeypogukkdpzfgadlooxgobllhmzeqaysdioszhisfjplivldjehxmslrmejxyqwfzuoitqtyxzywyziwfcudydwhcjmopsbzvcpbgsjmprfldcyolmbeaxtgoziqwvhiclajuhhsghajdegjfwlfpwokvsgnvsoczmpokdcqyjbfchajywpounhruncwqjwzxnqqgapcyzfybbsghxzcvujpqrclqqhwvfjtyzxehxxbzvzwhunfalgihihfxiurphzobffwufgfotmvtwlqsfdpipoaozdzyrlgpwmmqnvyrhuigjmjrwclklqtyxjujdcgfxbin";
    }
}
