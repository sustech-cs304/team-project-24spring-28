package org.example.backend;

import org.example.backend.app.AbstractUserApp;
import org.example.backend.domain.*;
import org.example.backend.dto.GlobalResponse;
import org.example.backend.dto.PostDto;
import org.example.backend.service.AbstractEnrollmentService;
import org.example.backend.service.AbstractUserService;
import org.example.backend.service.EventService;
import org.example.backend.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// This will not start the server, but will start the application context
//@SpringBootTest()
// This will start the server
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BackendApplicationTests {
    // This will inject the port that the server is running on
    @LocalServerPort
    private Integer port;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private AbstractUserService abstractUserService;
    @Autowired
    private AbstractEnrollmentService abstractEnrollmentService;
    @Autowired
    private EventService eventService;
    @Autowired
    private PostService postService;
    @Autowired
    private ProjectInfoAutoConfiguration projectInfoAutoConfiguration;


    @Test
    void generateData() {
        // generate users
        User user = new User();
        for (int i = 0; i < 8; i++) {
            user = new User();
            user.setUsername(String.valueOf(i));
            user.setPassword(String.valueOf(i));
            user.setName("user" + i);
            user.setBio("user" + i);
            Permission permission = new Permission();
            permission.setUser(user);
            permission.setCanCreate((i & 1) == 1);
            permission.setCanEnroll((i & 2) == 2);
            permission.setCanComment((i & 4) == 4);
            user.setPermission(permission);
            abstractUserService.saveUser(user);
        }

        // generate events
        // preform login user 7
        String token = (String) restTemplate.postForObject("http://localhost:" + port + "/login?username=7&password=7", null, GlobalResponse.class).getData();
        // add lecture
        Event lecture = new Event();
        CountEnrollment countEnrollment = new CountEnrollment();
        lecture.setTitle("����������������");
        lecture.setName("�����������⽲��");
        lecture.setIntroduction("�������뽲����");
        lecture.setPosterUrl("http://10.16.88.247:8082/image/env_poster.png");
        lecture.setText("\n" + "------\n" + "\n" + "# \uD83C\uDF3F ��ѧ������������ - ����ɫ�ж�������δ���� \uD83C\uDF3F\n" + "\n" + "## �������ǣ�һ��̽���ɳ�����չ�����ܣ�\n" + "\n" + "<img src='http://10.16.88.247:8082/image/env.png' width=300>\n" + "\n" + "### \uD83D\uDDD3\uFE0F ��������\n" + "\n" + "- **2024��8��5�� ����һ**\n" + "\n" + "### \uD83D\uDD52 ����ʱ��\n" + "\n" + "- **����3:00 - 5:00**\n" + "\n" + "### \uD83D\uDCCD �����ص�\n" + "\n" + "- **��ѧͼ��ݱ�����**\n" + "- ��ɽ��, �㶫ʡ\n" + "\n" + "### \uD83C\uDF99\uFE0F �����α�����\n" + "\n" + "#### �����\n" + "\n" + "- **ְλ**��������ѧϵ����\n" + "- **ר��**����̬ϵͳ������ɳ�����չ\n" + "- **�ɾ�**�������ƪ������̬���������ģ����ֶ�����Ҽ�������Ŀ\n" + "\n" + "#### �Ų�ʿ\n" + "\n" + "- **ְλ**��֪���������\n" + "- **ר��**�����пɳ����滮\n" + "- **�ɾ�**���ڹ��ʻ��������϶�η��ԣ��ƶ�������������ƶ�\n" + "\n" + "### \uD83C\uDF31 ��������\n" + "\n" + "- ����ν����У����ǽ�����̽�����¼����ؼ����⣺\n" + "\n" + "  1. **�ɳ������ʽ��ʵ��**\n" + "     - ̽���ճ������е���ɫѡ��\n" + "     - ������ܼ��ŵ�С����\n" + "     - ������μ������Ϻ�һ���Բ�Ʒ��ʹ��\n" + "  2. **��ɫ��Դ��δ��**\n" + "     - �˽�̫���ܡ����ܵȿ�������Դ�����½�չ\n" + "     - ������ɫ��Դ�Ծ��ú�����Ӱ��\n" + "     - ������ͳ��Դת�͵���ս�����\n" + "  3. **��������Ե���Ҫ��**\n" + "     - ������������ԶԵ�����̬ϵͳ������\n" + "     - ����ɹ���Ұ����ֲ�ﱣ������\n" + "     - ̽�ֳ��л������е���̬ƽ������\n" + "  4. **������������μ���̼�㼣**\n" + "     - ���������̻��ͳ���ũҵ�İ���\n" + "     - ���۹�����ͨ����ɫ���е�����\n" + "     - �ṩ��ͥ��Ч�����Ľ���\n" + "  5. **��������������**\n" + "     - �����ǰ�������ɿ��\n" + "     - ������������ƶ������ж�\n" + "     - ̽�ֹ�����β��뻷�����ߵ��ƶ�\n" + "  6. **��ɫ�Ƽ��Ĵ���**\n" + "     - չʾ�����Ƽ������·���\n" + "     - ���ۿƼ���ΰ������Ǹ��õر�������\n" + "     - ������ɫ�Ƽ��ķ�չ����\n" + "\n" + "  ------\n" + "\n" + "  ��Щ���ݽ������ǵļα�ͨ�����ʵ��ݽ��ͻ������ڳ��ָ���ҡ������ڴ����Ĳ��룬һ��Ϊ�������ǵĵ�����������\n" + "\n" + "### \uD83D\uDCDA �����߽����\n" + "\n" + "- **��ѻ������ϰ�**\n" + "- **����֤��**\n" + "- **��ɫֲ��һ��**\n" + "\n" + "### \uD83D\uDCDD ������ʽ\n" + "\n" + "��������������߱�����\n" + "\n" + "\n" + "### ? ������ֹ\n" + "\n" + "**2024��7��30��**\n" + "\n" + "------\n" + "\n" + "\uD83C\uDF1F **������Я��Ϊ�����δ���������������������μӣ�** \uD83C\uDF1F\n" + "\n" + "\n" + "\n" + "\u200B              ");
        lecture.setAuthor(user);
        lecture.setStartTime(LocalDateTime.of(2021, 6, 15, 0, 0, 0));
        lecture.setEndTime(LocalDateTime.of(2021, 6, 16, 0, 0, 0));
        countEnrollment.setEvent(lecture);
        countEnrollment.setStartTime(LocalDateTime.of(2021, 6, 1, 0, 0, 0));
        countEnrollment.setEndTime(LocalDateTime.of(2024, 6, 1, 0, 0, 0));
        countEnrollment.setCapacity(100);
        lecture.setAbstractEnrollment(countEnrollment);
        // save lecture
        eventService.saveEvent(lecture);

        // add soccer
        Event soccer = new Event();
        FormEnrollment formEnrollment = new FormEnrollment();
        soccer.setTitle("һ��������ɣ�");
        soccer.setName("У԰������");
        soccer.setIntroduction("У԰�����������������������չ��ɣ�");
        soccer.setPosterUrl("http://10.16.88.247:8082/image/soccer_poster.png");
        soccer.setText("------\n" + "\n" + "# \uD83C\uDFC6 ��ѧ���������������ѿ����� \uD83C\uDFC6\n" + "\n" + "## һ��չ�ּ��ɣ��ŶӾ���ͼ���ı�����\n" + "\n" + "### \uD83D\uDCC5 ��������\n" + "\n" + "- **Ԥѡ��**��2024��7��12��\n" + "- **�����**��2024��7��19��\n" + "- **����**��2024��7��26��\n" + "\n" + "### \uD83D\uDCCD �����ص�\n" + "\n" + "- **������������**\n" + "- ��ɽ��, �㶫ʡ\n" + "\n" + "### \uD83D\uDCDD ����Ҫ��\n" + "\n" + "- ��������У��ѧ��\n" + "- ÿ������������ **11��**\n" + "- �ṩ�������Ŷ���Ϣ����ϵ��ʽ\n" + "\n" + "### \uD83C\uDF96\uFE0F ��������\n" + "\n" + "- **�ھ�**������ **��5000** + ����\n" + "- **�Ǿ�**������ **��3000**\n" + "- **����**������ **��1000**\n" + "\n" + "<img src=\"http://10.16.88.247:8082/image/soccer.png\" width=\"500\">\n" + "\n" + "### \uD83D\uDCE2 ��α���\n" + "\n" + "����д�������\n" + "\n" + "```markdown\n" + "- �Ŷ����ƣ�\n" + "- �ӳ�������\n" + "- ��ϵ�绰��\n" + "- ��Ա������\n" + "  - ��Ա1\n" + "  - ��Ա2\n" + "  - ...\n" + "```\n" + "\n" + "### ? ������ֹ����\n" + "\n" + "**2024��6��30��**\n" + "\n" + "���������Ϣ��鿴�������ӣ�\n" + "\n" + "[����](https://soccer.hupu.com/)\n" + "\n" + "------\n" + "\n" + "\uD83D\uDD25 **��Ҫ������չ����������Ż��Ļ��ᣡ���������μӰɣ�** \uD83D\uDD25\n" + "\n" + "------\n" + "\n");
        soccer.setAuthor(user);
        soccer.setStartTime(LocalDateTime.of(2021, 6, 15, 0, 0, 0));
        soccer.setEndTime(LocalDateTime.of(2021, 6, 16, 0, 0, 0));
        soccer.setAbstractEnrollment(formEnrollment);
        formEnrollment.setEvent(soccer);
        formEnrollment.setStartTime(LocalDateTime.of(2021, 6, 1, 0, 0, 0));
        formEnrollment.setEndTime(LocalDateTime.of(2024, 6, 1, 0, 0, 0));
        formEnrollment.setDefinedFormEntries(new ArrayList<>());
        soccer.setAbstractEnrollment(formEnrollment);
        // add FormEntry
        List<DefinedFormEntry> definedFormEntries = formEnrollment.getDefinedFormEntries();
        //name
        DefinedFormEntry definedFormEntry = new DefinedFormEntry();
        definedFormEntry.setEntryId(0);
        definedFormEntry.setName("name");
        definedFormEntry.setType("input");
        definedFormEntry.setRequired(true);
        definedFormEntries.add(definedFormEntry);
        //sid
        definedFormEntry = new DefinedFormEntry();
        definedFormEntry.setEntryId(1);
        definedFormEntry.setName("sid");
        definedFormEntry.setType("input");
        definedFormEntry.setRequired(true);
        definedFormEntries.add(definedFormEntry);
        //gender
        definedFormEntry = new DefinedFormEntry();
        definedFormEntry.setOptions(new ArrayList<>());
        List<String> options = definedFormEntry.getOptions();
        options.add("male");
        options.add("female");
        definedFormEntry.setEntryId(2);
        definedFormEntry.setName("gender");
        definedFormEntry.setType("select");
        definedFormEntry.setRequired(true);
        definedFormEntries.add(definedFormEntry);
        //gpa
        definedFormEntry = new DefinedFormEntry();
        definedFormEntry.setEntryId(3);
        definedFormEntry.setName("gpa");
        definedFormEntry.setType("input");
        definedFormEntry.setRequired(true);
        definedFormEntries.add(definedFormEntry);
        //save soccer
        eventService.saveEvent(soccer);

        // generate posts
        // add lecture post1
        Post post = new Post();
        post.setUser(user);
        ArrayList<AbstractUser> likeUsers = new ArrayList<>();
        likeUsers.add(user);
        post.setLikeUsers(likeUsers);
        post.setEvent(lecture);
        post.setPostTitle("��ѧ������������ - ����ɫ�ж�������δ����");
        post.setPostContent("# \uD83C\uDF3F ��ѧ������������ - ����ɫ�ж�������δ���� \uD83C\uDF3F\n" + "\n" + "8��5�����磬�����ڴ�ѧͼ��ݱ������μ���һ��������Ļ���������������λ�����α�����ں��Ų�ʿ�������������˽��˿ɳ�����չ����Ҫ�ԣ���Ϊ���Ǵ����˺ܶ��������ʾ��ʵ�����顣\n" + "\n" + "### ��������ع�\n" + "\n" + "#### 1. **�ɳ������ʽ��ʵ��**\n" + "\n" + "�������ϸ������������ճ�������������ɫѡ����������һЩ�����еĽ��ܼ���С���ɣ������������ʹ�ú��ƹ���ظ����õĲ�Ʒ����Щ���鲻��ʵ�ã�����������ʶ��ÿһ��С�ı䶼���Դ�����ͬ��\n" + "\n" + "#### 2. **��ɫ��Դ��δ��**\n" + "\n" + "�Ų�ʿΪ����չʾ��̫���ܡ����ܵȿ�������Դ�����½�չ������������Щ��ɫ��Դ�Ծ��ú����Ļ���Ӱ�졣�ر��ǹ��ڴ�ͳ��Դת�͵����ۣ������ǿ����ڻ�����ҵ�пƼ����µ���Ҫ�ԡ�\n" + "\n" + "#### 3. **��������Ե���Ҫ��**\n" + "\n" + "����ڽ�������������ԶԵ�����̬ϵͳ�����壬��������һЩ�ɹ���Ұ����ֲ�ﱣ���������ⲿ�����������������ʶ��������������Բ�����Ϊ�˻���������Ϊ�����ǵ�δ����\n" + "\n" + "#### 4. **������������μ���̼�㼣**\n" + "\n" + "�Ų�ʿ�����������������̻��ͳ���ũҵ�İ������������˹�����ͨ����ɫ���е����ơ������ṩ������ͥ��Ч������ʵ�ý��飬������֪��ÿ���˶�����Ϊ�����������ס�\n" + "\n" + "### ��л���ջ�\n" + "\n" + "��ν��������ḻ�����ǵ�֪ʶ�������������ǶԻ��������顣ÿһλ�����߶��������ѻ������ϰ�������֤���һ����ɫֲ���ЩС���ﲻ���Ǽ���Ʒ�����������ж��Ŀ�ʼ��\n" + "\n" + "### δ�����ж�\n" + "\n" + "ͨ����ν��������������ʶ�����������Ľ����Ժ���Ҫ�ԡ�δ�������ǽ�����ע����ɫ���ʽ���������뻷�������ͬΪ�����δ������������\n" + "\n" + "��л���в����ߺͽ�����֯�ߣ�������Я��Ϊ�����δ��Ŭ����\n" + "\n" + "\uD83C\uDF1F **�����ǹ�ͬΪ���������׳�һ��������** \uD83C\uDF1F\n" + "\n" + "![������������](http://10.16.88.247:8082/image/env.png)\n" + "\n" + "���໷����Ϣ\n" + "\n" + "�������ڴ���һ�εĻ�����������ͬѧϰ����ͬ������");
        // save lecture post1
        postService.savePost(post);

        // add lecture post2
        post = new Post();
        post.setUser(user);
        likeUsers = new ArrayList<>();
        likeUsers.add(user);
        post.setLikeUsers(likeUsers);
        post.setEvent(lecture);
        post.setPostTitle("������������");
        post.setPostContent("8��5�����磬��ѧͼ��ݱ�����ӭ����һ������Ƿ��Ľ�����������ɫ�ж�������δ��������λ�������α�����ں��Ų�ʿ�ľ����ݽ����������������˽��˿ɳ�����չ�ķ������档\n" + "\n" + "### ����˲��ع�\n" + "\n" + "#### **����ڵĻ����ǻ�**\n" + "\n" + "�������Ϊ������ѧϵ���Σ������ݽ�������Ȥ��������̬ϵͳ����Ϳɳ�����չ�ĽǶȣ����������ʵ�ʿ��еĻ������������������ǣ��Ӽ���һ���Բ�Ʒʹ�õ��ƹ���ɫ���У�ÿһ��СС�ĸı䶼�ܴ����޴�Ļ���Ч�档\n" + "\n" + "#### **�Ų�ʿ��ǰհ��Ұ**\n" + "\n" + "�Ų�ʿ�Ľ�����������ڳ��пɳ����滮����ɫ��Դ��δ���������������µ�̫���ܺͷ��ܼ���������������Щ��ɫ��Դ�ھ��ú�����е�Ӧ��ʵ�����Ų�ʿ���ᵽ�˻������ɺ����ߵ��ƶ����̣��������ǻ������뵽�������ߵ��ƶ��С�\n" + "\n" + "### �����ĺ�������\n" + "\n" + "- �ɳ������ʽ��ʵ��\n" + "  - �˽�������ճ�������������ɫѡ�񣬼������Ϻ�һ���Բ�Ʒ��ʹ�á�\n" + "- ��ɫ��Դ��δ��\n" + "  - ̽��̫���ܡ����ܵȿ�������Դ�����½�չ�������ᾭ�õ�Ӱ�졣\n" + "- ��������Ե���Ҫ��\n" + "  - ������������Զ���̬ϵͳ�����弰�ɹ�����������\n" + "- ������������μ���̼�㼣\n" + "  - ���������̻�������ũҵ����ɫ���еİ����ͽ��顣\n" + "- ��������������\n" + "  - �����ǰ�������ɿ�ܣ�̽����������ƶ������ж���\n" + "- ��ɫ�Ƽ��Ĵ���\n" + "  - չʾ���µĻ����Ƽ������������䷢չ���ơ�\n" + "\n" + "### �������ջ�\n" + "\n" + "��ν�������������ѧ���˷ḻ�Ļ���֪ʶ���������Ǹ���̵�����˻�����������Ҫ�ԡ�����������ÿλ�����߶��յ��˻������ϰ�������֤���һ����ɫֲ���ЩС���ﲻ���Ǽ�����������ж��Ķ�����\n" + "\n" + "### �ж������ڿ�ʼ\n" + "\n" + "ͨ����ν��������Ǹ��Ӽᶨ�˼�����ɫ����ľ��ġ�δ�������ǻ�������뻷�������С�����𣬼���̼�㼣��Ϊ�����δ���������ǵ�������\n" + "\n" + "��л���в����ߺͽ�������֯�ߣ�������Я��Ϊ��������Ŭ����\n" + "\n" + "\uD83C\uDF1F **һ���ж�������Ϊ�����δ����һ������** \uD83C\uDF1F");
        // save lecture post2
        postService.savePost(post);

        // add soccer post1
        post = new Post();
        post.setUser(user);
        likeUsers = new ArrayList<>();
        likeUsers.add(user);
        post.setLikeUsers(likeUsers);
        post.setEvent(soccer);
        post.setPostTitle("��ѧ�������������ǵ���������");
        post.setPostContent("### ��ѧ�������������ǵ���������\n" + "\n" + "��ѧ���������ո������Ļ��ÿһ�������߶��������������˲���ĥ��Ļ��䡣��Ϊһ��������Ա���������һЩ��·���̺�����˲�䡣\n" + "\n" + "��Ԥѡ����������ÿһ�����������ż��ҡ�**7��12��**��Ԥѡ��������ȫ���Ž�һ�£��˷����������ѣ�����ͦ��������������������Ȼ���ȣ������ǵļ�����ӳ��ȡ�\n" + "\n" + "**7��19��**�İ�������������ٵ������ս������ʵ��ǿ���������쳣��ࡣ�������������ʱ��ϧ�ܣ�������û���ź�����Ϊ����ƴ��ȫ����չ�������״̬��\n" + "\n" + "**7��26��**�ľ�����Ȼû�����Ƕӵ���Ӱ���������ڳ���Ϊ������������ź�����ͬ��֤�˹ھ��ĵ�������������Ѫս�ӡ����𽱱�����һ�̣����������˶����ܵ����Ƿ������ķ�����\n" + "\n" + "��α��������Ƕ������������Ŀ��飬���Ƕ��Ŷ�Э������־����ĥ�¡������ջ������꣬��ǿ���ŶӾ�������Ҫ���ǣ����Ǹ����Ȱ������˶���\n" + "\n" + "��л��ѧ���������������ṩ������һ��չʾ���ҵ�ƽ̨���ڴ�����ı������ڴ��������ٴβ�����ս����ȡ���õĳɼ���\n" + "\n" + "\uD83C\uDFC6 **��ѧ�������������������ټ���** \uD83C\uDFC6");
        // save soccer post1
        postService.savePost(post);

        // add soccer post2
        post = new Post();
        post.setUser(user);
        likeUsers = new ArrayList<>();
        likeUsers.add(user);
        post.setLikeUsers(likeUsers);
        post.setEvent(soccer);
        post.setPostTitle("ȼ��عˣ���ѧ���������ĸж�˲�䣡");
        post.setPostContent("### ȼ��عˣ���ѧ���������ĸж�˲�䣡\n" + "\n" + "��ѧ��������Բ��������������һ��ع���γ���������ж�������֮�ðɣ�\n" + "\n" + "\uD83C\uDFC6 **�ھ�**����ϲ����Ѫս�ӡ��ٻ�ھ�����ն���񽱽�5000���������������ǵ��ŶӺ�������ǿƴ���������˾��壡\n" + "\n" + "\uD83E\uDD48 **�Ǿ�**�����籩�ӡ����ֳ�ɫ����ƴ�����һ�̣����ջ���Ǿ������ջ�3000�Ľ������ǵķܶ��������˶��ݣ�\n" + "\n" + "\uD83E\uDD49 **����**�������ܶӡ��ڱ�����չ�ֳ����õļ������Ŷ�Ĭ��������˼��������٣�ͬʱҲ����ˣ�1000�Ľ���\n" + "\n" + "ÿ֧�������鶼�ú�ˮ�ͼ�����д���Լ����������룬�ڱ������ջ������ꡢ�ŶӾ���ͳɳ���������Щ�ж�˲�䣬�ô�ѧ���������������������ֵ��\n" + "\n" + "�����ڴ�����һ��ı���������ļ��飬�������ҫ����лÿһλ�����ߵĸ�����֧�֣�������һ���֤������������ţ�\n" + "\n" + "[���˽��������������µ���Ϣ���������](https://soccer.hupu.com/)\n" + "\n" + "��ͬ�����������ĻԻ�˲�䣡\uD83C\uDF1F");
        // save soccer post2
        postService.savePost(post);
    }


//    @Test
//    void userTest() {
//        Admin admin = new Admin();
//        admin.setUsername("admin");
//        admin.setPassword("admin");
//        admin.setName("admin");
//        admin.setBio("admin");
//        abstractUserService.saveUser(admin);
//        for (int i = 0; i < 8; i++) {
//            User user = new User();
//            user.setUsername(String.valueOf(i));
//            user.setPassword(String.valueOf(i));
//            user.setName("user" + i);
//            user.setBio("user" + i);
//            Permission permission = new Permission();
//            permission.setUser(user);
//            permission.setCanCreate((i & 1) == 1);
//            permission.setCanEnroll((i & 2) == 2);
//            permission.setCanComment((i & 4) == 4);
//            user.setPermission(permission);
//            abstractUserService.saveUser(user);
//        }
//    }
//
//    @Test
//    void LoginTest() {
//        String token = restTemplate.postForObject("http://localhost:" + port + "/login?username=1&password=1", null, String.class);
//    }
//
//    @Test
//    void MessageTest() {
//        // preform login
//        String token1 = (String) restTemplate.postForObject("http://localhost:" + port + "/login?username=1&password=1", null, GlobalResponse.class).getData();
//        String token2 = (String) restTemplate.postForObject("http://localhost:" + port + "/login?username=2&password=2", null, GlobalResponse.class).getData();
//        // test the sendChat api
//        try {
//            mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/message/sendChat")
//                    .header("Authorization", token1)
//                    .param("userId", "2")
//                    .param("content", "hello2")
//                    .param("time", "2021-05-01 00:00:00")
//            ).andExpect(MockMvcResultMatchers.status().isOk());
//            mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/message/sendChat")
//                    .header("Authorization", token2)
//                    .param("userId", "1")
//                    .param("content", "hello1")
//                    .param("time", "2021-05-02 00:00:00")
//            ).andExpect(MockMvcResultMatchers.status().isOk());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void EventTest() {
//        // preform login
//        String token = (String) restTemplate.postForObject("http://localhost:" + port + "/login?username=3&password=3", null, GlobalResponse.class).getData();
//        // test the releaseEvent api
//        try {
//            mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/event/create")
//                    .header("Authorization", token)
//                    .param("title", "event1")
//                    .param("name", "event-name1")
//                    .param("applyStartTime", "2021-06-05 00:00:00")
//                    .param("applyEndTime", "2021-06-14 00:00:00")
//                    .param("startTime", "2021-06-15 00:00:00")
//                    .param("endTime", "2021-06-16 00:00:00")
//                    .param("introduction", "event-introduction1")
//                    .param("imageUrl", "")
//                    .param("mdText", "")
//                    .param("enrollmentType", "count")
//                    .param("limitCount", "100")
//            ).andExpect(MockMvcResultMatchers.status().isOk());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void PostTest() {
//
//    }

}
